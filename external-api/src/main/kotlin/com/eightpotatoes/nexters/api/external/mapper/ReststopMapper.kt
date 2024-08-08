package com.eightpotatoes.nexters.api.external.mapper

import com.eightpotatoes.nexters.api.external.mapper.MenuMapper.buildReststopMenuData
import com.eightpotatoes.nexters.api.external.model.*
import com.eightpotatoes.nexters.core.entity.Menu
import com.eightpotatoes.nexters.core.entity.Reststop
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
            foodMenusCount = foodMenusCount
        )
    }

    fun toReststopDetailResponse(
        reststop: Reststop,
        menus: List<Menu>,
        brands: List<BrandData>
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
                amenities = createAmenities(), // TODO 편의시설 데이터 추가 후 작업
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
                restaurantName = "식당가 (라면/우동)",
                operatingTime = "09:00 ~ 21:00"
            ),
            RestaurantInfo(
                restaurantName = "달콤커피 커피전문점",
                operatingTime = "08:00 ~ 22:00"
            )
        )
    }

    // Dummy 편의시설 데이터 리스트 생성
    private fun createAmenities(): List<AmenityData> {
        return listOf(
            AmenityData(
                amenityName = "화장실",
                amenityLogoUrl = "https://github.com/user-attachments/assets/0f2a2a5b-33a5-4d89-bacb-9a4f55b935de"
            ),
            AmenityData(
                amenityName = "ATM",
                amenityLogoUrl = "https://github.com/user-attachments/assets/0f2a2a5b-33a5-4d89-bacb-9a4f55b935de"
            )
        )
    }
}
