package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ChargingStationOrigin(
    @JsonProperty("수소차 충전소")
    val hydrogenChargingStation: String, // X

    @JsonProperty("시설구분")
    val facilityType: String, // 휴게소

    @JsonProperty("시설명")
    val facilityName: String, // 강릉(인천)

    @JsonProperty("전기차 충전소")
    val electricChargingStation: String, // O

    @JsonProperty("지역")
    val region: String
)
