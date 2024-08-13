package com.eightpotatoes.nexters.api.external.service

import com.eightpotatoes.nexters.api.external.mapper.ReststopMapper
import com.eightpotatoes.nexters.api.external.model.*
import com.eightpotatoes.nexters.core.model.Location
import com.eightpotatoes.nexters.core.repository.*
import com.eightpotatoes.nexters.core.util.ReststopUtils.isRestaurantOpen
import org.springframework.stereotype.Service

import reactor.core.publisher.Flux
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Service
class ReststopExternalService(
    private val reststopRepository: ReststopRepository,
    private val menuRepository: MenuRepository,
    private val brandRepository: BrandRepository,
    private val convenientFacilityRepository: ConvenientFacilityRepository,
) {
    fun getReststopsAtHighways(
        roadNameList: List<String>,
        direction: String,
        midPoint: Location,
        middleZone: Pair<Location, Location>,
    ): Flux<ReststopsAtHighway> {
        val reststopDetailAtHighwayList = mutableListOf<ReststopDetailAtHighway>()

        val reststopList = reststopRepository.findByRoadRouteNameAndDirection(roadNameList, direction)
        reststopList.iterator().forEach {
            val reststopsAtHighway = ReststopMapper.toReststopsAtHighway(
                entity = it,
                isOperating = isRestaurantOpen(it.restaurantOpenTime ?: "00:00 ~ 23:59"),
                foodMenusCount = calculateFoodMenusCount(it.standardCode),
            )
            reststopDetailAtHighwayList.add(reststopsAtHighway)
        }
        sortReststopsByProximity(reststopDetailAtHighwayList, midPoint)
        val filterReststopsInMiddleZone = filterReststopsInMiddleZone(reststopDetailAtHighwayList, middleZone)
        val recommendReststop = calculateScoreAndRecommendReststop(filterReststopsInMiddleZone, midPoint)
        // 추천 휴게소 지정
        reststopDetailAtHighwayList.forEach {
            it.isRecommend = it.code == recommendReststop?.code
        }
        return Flux.just(ReststopsAtHighway(reststopList.size, reststopDetailAtHighwayList))
    }

    private fun calculateFoodMenusCount(reststopCode: String?): Int {
        return menuRepository.findByReststopCode(reststopCode).size
    }

    fun getReststopInfo(reststopCode: String): Flux<ReststopDetailResponse> {
        // 휴게소 정보 조회
        val reststop = reststopRepository.findByStandardCode(reststopCode)
            ?: throw IllegalArgumentException("Reststop not found")
        // 휴게소 메뉴 조회
        val menus = menuRepository.findByReststopCode(reststopCode)
        // 입점 브랜드 조회
        val brands = brandRepository.findByReststopCode(reststopCode).map {
            BrandData(
                brandName = it.name,
                brandLogoUrl = it.thumbnailUrl
            )
        }
        // 편의시설 조회
        val amenities = convenientFacilityRepository.findByStandardCode(reststopCode).map {
            AmenityData(
                amenityName = it.name,
                amenityLogoUrl = "TEST", // TODO 편의시설 image 링크 추가
            )
        }

        return Flux.just(ReststopMapper.toReststopDetailResponse(reststop, menus, brands, amenities))
    }

    // 노선과 휴게소 위치 기준 : 경로 내 휴게소 필터링(중간 휴게소 찾기)
    private fun filterReststopsInMiddleZone(
        reststops: List<ReststopDetailAtHighway>,
        middleZone: Pair<Location, Location>,
    ): List<ReststopDetailAtHighway> {
        val (zoneStart, zoneEnd) = middleZone

        return reststops.filter { reststop ->
            val latitude = reststop.location.latitude
            val longitude = reststop.location.longitude
            // zoneStart.latitude, zoneEnd.latitude 좌표 중 더 작은 값을 startLat으로, 큰 값을 endLat으로 설정
            val (startLat, endLat) = if (zoneStart.latitude < zoneEnd.latitude) {
                zoneStart.latitude to zoneEnd.latitude
            } else {
                zoneEnd.latitude to zoneStart.latitude
            }
            // zoneStart.longitude, zoneEnd.longitude 좌표 중 더 작은 값을 startLong으로, 큰 값을 endLong으로 설정
            val (startLong, endLong) = if (zoneStart.longitude < zoneEnd.longitude) {
                zoneStart.longitude to zoneEnd.longitude
            } else {
                zoneEnd.longitude to zoneStart.longitude
            }

            latitude in startLat..endLat && longitude in startLong..endLong
        }
    }

    private fun calculateScoreAndRecommendReststop(
        reststops: List<ReststopDetailAtHighway>,
        midPoint: Location,
    ): ReststopDetailAtHighway? {
        return reststops.maxByOrNull { reststop ->
            val ratingWeight = 0.6
            val locationWeight = 0.4

            val distanceToMidPoint = calculateDistance(midPoint, reststop.location)
            val maxDistance = 100.0  // 최대 거리 가중치 기준 (적당한 값 설정 필요)
            val naverRating = reststop.naverRating?:1.0F
            val ratingScore = naverRating.times(ratingWeight)
            val locationScore = ((maxDistance - distanceToMidPoint) / maxDistance) * locationWeight

            ratingScore.plus(locationScore)
        }
    }

    fun calculateDistance(location1: Location, location2: Location): Double {
        val radius = 6371e3 // 지구 반지름(미터)
        val φ1 = Math.toRadians(location1.latitude.toDouble())
        val φ2 = Math.toRadians(location2.latitude.toDouble())
        val Δφ = Math.toRadians((location2.latitude - location1.latitude).toDouble())
        val Δλ = Math.toRadians((location2.longitude -location1.longitude).toDouble())

        val a = sin(Δφ / 2) * sin(Δφ / 2) +
                cos(φ1) * cos(φ2) *
                sin(Δλ / 2) * sin(Δλ / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return radius * c / 1000 // 거리(km)
    }

    // 휴게소를 출발지에서 가까운 순으로 정렬
    fun sortReststopsByProximity(
        reststops: List<ReststopDetailAtHighway>,
        fromLocation: Location
    ): List<ReststopDetailAtHighway> =
        reststops.sortedBy { reststop ->
            calculateDistance(fromLocation, reststop.location)
        }
}