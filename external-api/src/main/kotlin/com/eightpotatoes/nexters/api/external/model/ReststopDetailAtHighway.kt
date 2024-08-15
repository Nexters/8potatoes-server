package com.eightpotatoes.nexters.api.external.model

import com.eightpotatoes.nexters.core.model.Location

data class ReststopDetailAtHighway(
    val name: String, // 휴게소 이름
    val direction: String?, // 방면
    val code: String, // 휴게소 코드
    val isOperating: Boolean, // 운영중
    val gasolinePrice: String?, // 휘발유 가격
    val dieselPrice: String?, // 경유 가격
    val lpgPrice: String?, // LPG 가격
    val naverRating: Float?, // 네이버 평점
    val foodMenusCount: Int, // 총 메뉴 개수
    val location: Location, // 좌표
    var isRecommend: Boolean, // 추천 휴게소 여부
)
