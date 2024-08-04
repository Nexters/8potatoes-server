package com.eightpotatoes.nexters.client.reststop.response

data class ReststopCodeOrigin(
    val direction: String? = null, // "부산"
    val pageNo: String? = null, // null
    val numOfRows: String? = null, // null
    val routeName: String? = null, // "경부선"
    val serviceAreaCode: String? = null, // "A00001"
    val serviceAreaName: String? = null, // "서울만남(부산)휴게소"
    val telNo: String? = null, // "02-578-3372"
    val brand: String? = null, // "탐앤탐스 외 3"
    val routeCode: String? = null, // "0010"
    val serviceAreaCode2: String? = null, // "000001"
    val svarAddr: String? = null, // "서울 서초구 원지동10-16"
    val convenience: String? = null, // "수유실"
    val maintenanceYn: String? = null, // "X"
    val truckSaYn: String? = null // "X"
)
