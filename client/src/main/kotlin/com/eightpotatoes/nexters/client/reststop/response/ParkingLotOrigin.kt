package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ParkingLotOrigin(
    @JsonProperty("대형")
    val largeCarSpace: Int,
    @JsonProperty("소형")
    val smallCarSpace: Int,
    @JsonProperty("장애인")
    val disabledPersonSpace: Int,
    @JsonProperty("노선")
    val route: String,
    @JsonProperty("본부")
    val headquarters: String,
    @JsonProperty("합계")
    val totalSpace: Int,
    @JsonProperty("휴게소명")
    val reststopName: String
)
