package com.eightpotatoes.nexters.client.reststop.response

data class  GasStationOrigin (
    val direction: String? = null,  // "부산"
    val pageNo: String? = null,  // null
    val numOfRows: String? = null,  // null
    val routeName: String? = null,  // "경부선"
    val serviceAreaCode: String? = null,  // "B00014"
    val serviceAreaName: String? = null,  // "안성(부산)주유소"
    val telNo: String? = null,  // "031-651-5385"
    val routeCode: String? = null,  // "0010"
    val oilCompany: String? = null,  // "AD"
    val lpgYn: String? = null,  // "Y"
    val gasolinePrice: String? = null,  // "1,652원"
    val diselPrice: String? = null,  // "1,492원"
    val lpgPrice: String? = null,  // "1,050원"
    val serviceAreaCode2: String? = null,  // "000010"
    val svarAddr: String? = null,  // "경기 안성시 원곡면반제리 137-3"
)
