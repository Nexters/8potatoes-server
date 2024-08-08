package com.eightpotatoes.nexters.api.external.model

data class GasStationData(
    val gasolinePrice: String?, // 휘발유 가격
    val dieselPrice: String?, // 경유 가격
    val lpgPrice: String?, // LPG 가격
    val isElectricChargingStation: Boolean, // 전기차 충전소 여부
    val isHydrogenChargingStation: Boolean // 수소차 충전소 여부
)