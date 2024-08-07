package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GasStationOrigin(
    val gasolinePrice: String? = null,  // "1,652원"
    @JsonProperty("diselPrice")
    val dieselPrice: String? = null,  // "1,492원"
    val lpgPrice: String? = null,  // "1,050원"
    @JsonProperty("serviceAreaCode2")
    val standardCode: String? = null, // 휴게소/주유소코드
    // unuseful
    val oilCompany: String? = null,  // "AD"
    val telNo: String? = null,  // "031-651-5385"
    val svarAddr: String? = null,  // "경기 안성시 원곡면반제리 137-3"
    val direction: String? = null,  // "부산"
    val routeName: String? = null,  // "경부선"
    val serviceAreaCode: String? = null,  // "B00014"
    val serviceAreaName: String? = null,  // "안성(부산)주유소"
    val routeCode: String? = null,  // "0010"
    val lpgYn: String? = null,  // "Y"
)
