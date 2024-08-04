package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class ReststopCodeOriginBody(
    val count: Int? = null,
    val list: List<ReststopCodeOrigin>? = null
)
