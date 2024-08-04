package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.*

@Table(name = "reststop")
@Entity
class Reststop(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(name = "reststop_code")
        var reststopCode: String? = null,
        val name: String,
        val roadKind: String,
        val roadRouteNo: String,
        val roadRouteName: String,
        val roadRouteDirection: String,
        val destinationDirection: String,
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
        val hasToilet: Boolean,
        var gasolinePrice: String? = null, // 휘발유 가격
        var dieselPrice: String? = null, // 경유 가격
        var lpgPrice: String? = null, // LPG 가격
        var routeCode: String? = null, // 노선코드
        var gasStationCode: String? = null, // 주유소 코드
        var naverRating: Float? = null, // 네이버평점
) : BaseDateTimeEntity()
