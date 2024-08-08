package com.eightpotatoes.nexters.api.external.model

data class MenuData(
    val menuName: String, // 메뉴 이름
    val menuPrice: Int, // 메뉴 가격
    val isSignatureMenu: Boolean, // 시그니처 메뉴 여부
    val isPopularMenu: Boolean, // 인기 메뉴 여부
    val menuCategory: String, // 메뉴 카테고리
)