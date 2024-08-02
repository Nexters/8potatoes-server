package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.*

@Table(name = "rest_stop")
@Entity
class Reststop(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val name: String,
        val roadKind: String,
        val roadRouteNo: String,
        val roadRouteName: String,
        val roadRouteDirection: String,
        val reststopType: String,
        val latitude: Float,
        val longitude: Float,
        val openTime: String,
        val closeTime: String,
        val phoneNumber: String,
        val hasGasStation: Boolean,
        @Column(name = "has_lpg_charging_station")
        val hasLPGChargingStation: Boolean,
        val hasElectricChargingStation: Boolean,
        val hasPharmacy: Boolean,
        val hasRestaurant: Boolean,
        val hasNursingRoom: Boolean,
        val hasToilet: Boolean
) : BaseDateTimeEntity()
