package com.eightpotatoes.nexters.api.external.model

data class ReststopMenuData(
    val representativeMenuData: List<RepresentativeMenuData>, // 대표 메뉴 데이터(최대 2개)
    val totalMenuCount: Int, // 전체 메뉴 개수
    val recommendedMenuData: List<MenuData>, // 추천 메뉴 데이터
    val normalMenuData: List<MenuData>, // 일반 메뉴 데이터
)
