package com.eightpotatoes.nexters.core.model

import com.eightpotatoes.nexters.core.entity.Menu
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReststopFood(
    val stdRestCd: String,       // 휴게소/주유소코드
    val routeCd: String,         // 노선코드
    val svarAddr: String,        // 휴게소주소
    val restCd: String,          // 휴게소코드
    val routeNm: String,         // 노선명
    val stdRestNm: String,       // 휴게소/주유소명
    val lsttmAltrUser: String,   // 최종수정사용자
    val lsttmAltrDttm: String,   // 최종수정일시
    val seq: String,             // 음식 시퀀스
    val foodNm: String,          // 음식 이름
    val foodCost: String,        // 음식 가격
    val etc: String?,            // 상세내역
    val recommendyn: String,     // 추천메뉴 구분
    val seasonMenu: String,      // 계절메뉴 구분 (4:사계절, S:여름, W:겨울)
    val bestfoodyn: String,      // 베스트푸드 구분
    val premiumyn: String,       // 프리미엄메뉴 구분
    val app: String,             // 하이쉼마루 앱 표출 여부
    val foodMaterial: String?,   // 요리재료
    val lastId: String,          // 최초등록자
    val lastDtime: String        // 최초등록일시
) {
    fun toMenu(): Menu {
        return Menu(
            id = 0,
            foodSeq = seq,
            name = foodNm,
            price = foodCost.toIntOrNull() ?: 0,
            description = etc,
            isRecommended = recommendyn.equals("Y", ignoreCase = true),
            isPremium = premiumyn.equals("Y", ignoreCase = true),
            isBestFood = bestfoodyn.equals("Y", ignoreCase = true),
            reststopCode = stdRestCd,
            category = MenuCategory.UNKNOWN
        )
    }
}