package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class ReststopOriginBody(
    val items: List<ReststopOrigin>? = null,
    val totalCount: String? = null,
    val numOfRows: String? = null,
    val pageNo: String? = null
)