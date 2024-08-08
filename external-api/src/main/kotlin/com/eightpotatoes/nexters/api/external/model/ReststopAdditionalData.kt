package com.eightpotatoes.nexters.api.external.model

data class ReststopAdditionalData(
    val restaurantOperatingTimes: List<RestaurantInfo>, // 식당가 영업시간 정보 리스트
    val brands: List<BrandData>, // 입점 브랜드 리스트
    val amenities: List<AmenityData>, // 편의시설 리스트
    val address: String, // 주소
    val phoneNumber: String, // 전화번호
)