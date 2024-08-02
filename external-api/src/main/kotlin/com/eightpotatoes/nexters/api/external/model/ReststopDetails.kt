package com.eightpotatoes.nexters.api.external.model

data class ReststopDetails(
    val name: String,
    val address: String,
    val isOperating: Boolean, // 운영중인지
    val gasolinePrice: String, // 휘발유 가격
    val dieselPrice: String, // 경유 가격
    val totalMenuCount: Int, // 총 메뉴 {N}가지
)
