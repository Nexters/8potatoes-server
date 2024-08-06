package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class ChargingStationOriginBody(
    val currentCount: Int? = null,
    val data: List<ChargingStationOrigin>? = null
)



