package com.eightpotatoes.nexters.api.external.model

data class ParkingData(
    val smallCarSpace: Int, // 소형 차 공간
    val largeCarSpace: Int, // 대형 차 공간
    val disabledPersonSpace: Int, // 장애인 주차 공간
    val totalSpace: Int, // 총 주차 공간
    val updateDate: String, // 데이터 업데이트 날짜
)