package com.eightpotatoes.nexters.api.external.model

data class ReststopDetailAtHighway(
    val name: String, // 휴게소 이름
    val isOperating: Boolean, // 운영중
    val gasolinePrice: String?, // 휘발유 가격
    val dieselPrice: String?, // 경유 가격
    val lpgPrice: String?, // LPG 가격
    val naverRating: Float?, // 네이버 평점
    val foodMenusCount: Int, // 총 메뉴 개수
)
