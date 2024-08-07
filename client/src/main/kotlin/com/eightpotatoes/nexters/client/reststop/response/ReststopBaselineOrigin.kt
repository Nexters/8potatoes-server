package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ReststopBaselineOrigin(
    @JsonProperty("serviceAreaName")
    val name: String? = null, // 휴게소/주유소명
    @JsonProperty("serviceAreaCode2")
    val standardCode: String? = null, // 휴게소/주유소코드
    val routeName: String? = null, // 노선명
    val routeCode: String? = null, // 노선코드
    val serviceAreaCode: String? = null, // 영업부대시설코드
    val svarAddr: String? = null, // 휴게소주소
    val telNo: String? = null, // 전화번호
    // unuseful
    val convenience: String? = null, // 편의시설
    val truckSaYn: String? = null, // 화물휴게소유무
    val maintenanceYn: String? = null, // 경정비유무
    val brand: String? = null, // 브랜드
)
