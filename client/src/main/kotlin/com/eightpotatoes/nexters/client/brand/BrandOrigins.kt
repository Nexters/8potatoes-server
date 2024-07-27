package com.eightpotatoes.nexters.client.brand

data class BrandOrigins(
    val list: List<BrandOrigin>,
    val count: String? = null,
    val pageNo: String? = null,
    val numOfRows: String? = null,
    val pageSize: String? = null,
    val message: String? = null,
    val code: String? = null
)
