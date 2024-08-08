package com.eightpotatoes.nexters.api.external.model

data class RepresentativeMenuData(
    val representativeMenuName: String, // 대표 메뉴 이름
    val representativeMenuPrice: Int, // 대표 메뉴 가격
    val representativeMenuImageUrl: String, // 대표 메뉴 이미지
    val representativeMenuDescription: String, // 대표 메뉴 설명
)
