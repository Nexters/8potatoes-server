package com.eightpotatoes.nexters.api.external.mapper

import com.eightpotatoes.nexters.api.external.mapper.MenuMapper.buildReststopMenuData
import com.eightpotatoes.nexters.api.external.model.*
import com.eightpotatoes.nexters.core.entity.Menu
import com.eightpotatoes.nexters.core.entity.Reststop
import com.eightpotatoes.nexters.core.model.Location
import com.eightpotatoes.nexters.core.util.ReststopUtils.isRestaurantOpen
import com.eightpotatoes.nexters.core.util.TimeUtils.formatLocalDateTime
import java.time.LocalDateTime

object ReststopMapper {
    fun toReststopsAtHighway(
        entity: Reststop,
        isOperating: Boolean,
        foodMenusCount: Int
    ): ReststopDetailAtHighway {
        return ReststopDetailAtHighway(
            name = entity.name,
            code = entity.standardCode,
            isOperating = isOperating,
            gasolinePrice = entity.gasolinePrice,
            dieselPrice = entity.dieselPrice,
            lpgPrice = entity.lpgPrice,
            naverRating = entity.naverRating,
            foodMenusCount = foodMenusCount,
            location = Location(entity.latitude, entity.longitude),
            isRecommend = false,
        )
    }

    fun toReststopDetailResponse(
        reststop: Reststop,
        menus: List<Menu>,
        brands: List<BrandData>,
        amenities: List<AmenityData>,
    ): ReststopDetailResponse {
        return ReststopDetailResponse(
            name = reststop.name,
            isOperating = isRestaurantOpen(reststop.restaurantOpenTime ?: "00:00 ~ 23:59"),
            startTime = reststop.restaurantOpenTime?.split(" ~ ")?.get(0),
            endTime = reststop.restaurantOpenTime?.split(" ~ ")?.get(1),
            naverRating = reststop.naverRating,
            gasStationData = GasStationData(
                gasolinePrice = reststop.gasolinePrice,
                dieselPrice = reststop.dieselPrice,
                lpgPrice = reststop.lpgPrice,
                isElectricChargingStation = reststop.hasElectricCharger,
                isHydrogenChargingStation = reststop.hasHydrogenCharger
            ),
            parkingData = ParkingData(
                smallCarSpace = reststop.smallCarSpace ?: 0,
                largeCarSpace = reststop.largeCarSpace ?: 0,
                disabledPersonSpace = reststop.disabledPersonSpace ?: 0,
                totalSpace = reststop.totalSpace ?: 0,
                updateDate = formatLocalDateTime(reststop.modifiedAt ?: LocalDateTime.now())
            ),
            reststopData = ReststopAdditionalData(
                restaurantOperatingTimes = createRestaurantOperatingTimes(), // TODO 식당 영업시간 데이터 추가 후 작업
                brands = brands,
                amenities = amenities,
                address = reststop.address ?: "",
                phoneNumber = reststop.phoneNumber,
            ),
            menuData = buildReststopMenuData(menus)
        )
    }

    // Dummy 식당 영업시간 데이터 리스트 생성
    private fun createRestaurantOperatingTimes(): List<RestaurantInfo> {
        return listOf(
            RestaurantInfo(
                restaurantName = "[TEST] 식당가 (라면/우동)",
                operatingTime = "09:00 ~ 21:00"
            ),
            RestaurantInfo(
                restaurantName = "[TEST] 달콤커피 커피전문점",
                operatingTime = "08:00 ~ 22:00"
            )
        )
    }
}
