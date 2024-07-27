package com.eightpotatoes.nexters.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReststopFoodResponse(
    val count: Int,
    val list: List<ReststopFood>,
)