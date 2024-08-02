package com.eightpotatoes.nexters.client.menu

import com.eightpotatoes.nexters.core.model.ReststopFood
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReststopFoodResponse(
    val count: Int,
    val list: List<ReststopFood>,
)