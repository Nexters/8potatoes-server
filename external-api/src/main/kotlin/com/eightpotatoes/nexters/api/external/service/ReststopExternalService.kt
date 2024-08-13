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
        val reststopList = reststopRepository.findByRoadRouteNameAndDirection(roadNameList, direction)
        val reststopDetailAtHighwayList = reststopList.map {
            ReststopMapper.toReststopsAtHighway(
                entity = it,
                isOperating = isRestaurantOpen(it.restaurantOpenTime ?: "00:00 ~ 23:59"),
                foodMenusCount = menuRepository.findByReststopCode(it.standardCode).size,
            )
        }
        val sortedReststops = sortReststopsByProximity(reststopDetailAtHighwayList, midPoint)
        val filteredReststops = filterReststopsInMiddleZone(sortedReststops, middleZone)
        val recommendReststop = calculateScoreAndRecommendReststop(filteredReststops, midPoint)

        reststopDetailAtHighwayList.forEach {
            it.isRecommend = it.code == recommendReststop?.code
        }
        return Flux.just(ReststopsAtHighway(reststopList.size, reststopDetailAtHighwayList))
    }

    fun getReststopInfo(reststopCode: String): Flux<ReststopDetailResponse> {
        val reststop = reststopRepository.findByStandardCode(reststopCode)
            ?: throw IllegalArgumentException("Reststop not found")
        val menus = menuRepository.findByReststopCode(reststopCode)
        val brands = brandRepository.findByReststopCode(reststopCode).map {
            BrandData(
                brandName = it.name,
                brandLogoUrl = it.thumbnailUrl
            )
        }
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
        val (startLat, endLat) = listOf(zoneStart.latitude, zoneEnd.latitude).sorted()
        val (startLong, endLong) = listOf(zoneStart.longitude, zoneEnd.longitude).sorted()

        return reststops.filter { reststop ->
            val latitude = reststop.location.latitude
            val longitude = reststop.location.longitude
            latitude in startLat..endLat && longitude in startLong..endLong
        }
    }

    private fun calculateScoreAndRecommendReststop(
        reststops: List<ReststopDetailAtHighway>,
        midPoint: Location,
    ): ReststopDetailAtHighway? {
        val ratingWeight = 0.6
        val locationWeight = 0.4
        val maxDistance = 100.0

        return reststops.maxByOrNull { reststop ->
            val distanceToMidPoint = calculateDistance(midPoint, reststop.location)
            val naverRating = reststop.naverRating ?: 1.0F
            val ratingScore = naverRating * ratingWeight
            val locationScore = ((maxDistance - distanceToMidPoint) / maxDistance) * locationWeight
            ratingScore + locationScore
        }
    }

    fun calculateDistance(location1: Location, location2: Location): Double {
        val radius = 6371e3 // 지구 반지름(미터)
        val φ1 = Math.toRadians(location1.latitude.toDouble())
        val φ2 = Math.toRadians(location2.latitude.toDouble())
        val Δφ = Math.toRadians((location2.latitude - location1.latitude).toDouble())
        val Δλ = Math.toRadians((location2.longitude - location1.longitude).toDouble())

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