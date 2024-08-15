package com.eightpotatoes.nexters.api.external.model

data class ReststopDetailResponse(
    val name: String, // 휴게소 이름
    val direction: String?, // 휴게소 방향
    val isOperating: Boolean, // 식당 운영중 여부
    val startTime: String?, // 식당 운영 시작 시간(07:00)
    val endTime: String?, // 식당 운영 종료 시간(23:30)
    val naverRating: Float?, // 네이버 평점
    val gasStationData: GasStationData, // 주유소 정보
    val parkingData: ParkingData, // 주차 정보
    val reststopData: ReststopAdditionalData, // 휴게소 기타 정보
    val menuData: ReststopMenuData, // 메뉴 데이터 리스트
)
