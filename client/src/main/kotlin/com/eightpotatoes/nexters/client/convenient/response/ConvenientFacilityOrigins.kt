package com.eightpotatoes.nexters.client.convenient.response

data class ConvenientFacilityOrigins(
        val list: List<ConvenientFacilityOrigin>,
        val count: String? = null,
        val pageNo: String? = null,
        val numOfRows: String? = null,
        val pageSize: String? = null,
        val message: String? = null,
        val code: String? = null
)
