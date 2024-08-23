package com.eightpotatoes.nexters.api.external.service

import com.eightpotatoes.nexters.api.external.mapper.ReststopMapper
import com.eightpotatoes.nexters.api.external.model.*
import com.eightpotatoes.nexters.core.entity.Reststop
import com.eightpotatoes.nexters.core.model.Location
import com.eightpotatoes.nexters.core.repository.*
import com.eightpotatoes.nexters.core.util.LocationUtils.calculateDistance
import com.eightpotatoes.nexters.core.util.ReststopUtils.isRestaurantOpen
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ReststopExternalService(
    private val reststopRepository: ReststopRepository,
    private val menuRepository: MenuRepository,
    private val brandRepository: BrandRepository,
    private val convenientFacilityRepository: ConvenientFacilityRepository,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${cloud.aws.s3.endpoint}")
    private val baseUrl: String? = null

    fun getReststopsAtHighways(
        fromLocation: Location,
        highwayRequest: HighwayRequest,
        direction: String,
        midPoint: Location,
        middleZone: Pair<Location, Location>,
    ): Flux<ReststopsAtHighway> {
        val reststopList = reststopRepository.findByDirection(direction)
        val boundingBoxes = mutableListOf<BoundingBox>()
        highwayRequest.highways.forEach { (_, highwaySectionList) ->
            highwaySectionList.forEach {
                require(it.size == 4) { "Highway section must have 4 coordinates" }
                val convertToBoundingBox = convertToBoundingBox(
                    it.map { coordinates ->
                        Location(
                            longitude = coordinates[0],
                            latitude = coordinates[1]
                        )
                    }
                )
                boundingBoxes.add(convertToBoundingBox)
            }
        }
        val filteredReststop = filterReststopsInBoundingBox(reststopList, boundingBoxes)

        val reststopDetailAtHighwayList = filteredReststop.map {
            ReststopMapper.toReststopsAtHighway(
                entity = it,
                isOperating = isRestaurantOpen(it.restaurantOpenTime ?: "00:00 ~ 23:59"),
                foodMenusCount = menuRepository.findByReststopCode(it.standardCode).size,
            )
        }
        val sortedReststops = sortReststopsByProximity(reststopDetailAtHighwayList, fromLocation)
        val filteredReststops = filterReststopsInMiddleZone(sortedReststops, middleZone)
        val recommendReststop = calculateScoreAndRecommendReststop(filteredReststops, midPoint)

        sortedReststops.forEach {
            it.isRecommend = it.code == recommendReststop?.code
        }
        return Flux.just(ReststopsAtHighway(sortedReststops.size, sortedReststops))
    }

    fun getReststopInfo(reststopCode: String): Mono<ReststopDetailResponse> {
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
                amenityLogoUrl = it.imageUrl?: ""
            )
        }

        return Mono.just(
            ReststopMapper.toReststopDetailResponse(
                reststop = reststop,
                menus = menus,
                brands = brands,
                amenities = amenities,
                baseUrl = "$baseUrl/$bucket",
            )
        )
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
        val maxDistance = 200.0

        return reststops.maxByOrNull { reststop ->
            val distanceToMidPoint = calculateDistance(midPoint, reststop.location)
            val naverRating = reststop.naverRating ?: 1.0F
            val ratingScore = naverRating * ratingWeight
            val locationScore = ((maxDistance - distanceToMidPoint) / maxDistance) * locationWeight
            ratingScore + locationScore
        }
    }


    // 휴게소를 출발지에서 가까운 순으로 정렬
    fun sortReststopsByProximity(
        reststops: List<ReststopDetailAtHighway>,
        fromLocation: Location
    ): List<ReststopDetailAtHighway> =
        reststops.sortedBy { reststop ->
            calculateDistance(fromLocation, reststop.location)
        }

    // Convert highway coordinates to BoundingBox
    private fun convertToBoundingBox(coords: List<Location>): BoundingBox {
        return BoundingBox(coords[0], coords[1], coords[2], coords[3])
    }

    // Filter reststops inside bounding boxes
    private fun filterReststopsInBoundingBox(
        reststops: List<Reststop>,
        boundingBoxes: List<BoundingBox>,
    ): List<Reststop> {
        return reststops.filter { reststop ->
            boundingBoxes.any { it.contains(Location(reststop.latitude, reststop.longitude)) }
        }
    }
}
