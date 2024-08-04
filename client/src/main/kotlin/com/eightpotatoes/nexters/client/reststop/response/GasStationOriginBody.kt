package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class  GasStationOriginBody (
    val count: Int? = null,
    val list: List<GasStationOrigin>? = null
)
