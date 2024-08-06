package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class  ReststopParkingLotOriginBody (
    val count: Int? = null,
    val list: List<ReststopParkingLotOrigin>? = null
)
