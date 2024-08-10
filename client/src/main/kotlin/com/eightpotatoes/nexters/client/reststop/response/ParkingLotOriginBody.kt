package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class ParkingLotOriginBody(
    val currentCount: Int,
    val data: List<ParkingLotOrigin>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)
