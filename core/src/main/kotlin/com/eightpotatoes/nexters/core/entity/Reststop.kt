package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.*

@Table(name = "reststop")
@Entity
class Reststop(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val standardCode: String, // stdRestCd(000001) : 표준 휴게소/주유소 공통 코드
        val name: String, // unitName(서울만남(부산)휴게소)
        val code: String, // unitCode(001)
        val routeName: String, // 경부선
        val routeNo: String, // 0010
        val longitude: Float, // xValue(127.042514)
        val latitude: Float, // yValue(37.459939)
        val serviceAreaCode: String, // serviceAreaCode(A00001) : 서비스 구역 코드
        val smallCarSpace: Int,
        val largeCarSpace: Int,
        val disabledPersonSpace: Int,
        val routeDirection: String,// 상행/하행
        var gasolinePrice: String? = null, // 휘발유 가격
        var dieselPrice: String? = null, // 경유 가격
        var lpgPrice: String? = null, // LPG 가격
        var address: String,
        var phoneNumber: String,
        val hasElectricCharger: Boolean = false, // 전기차 충전소 여부
        val hasHydrogenCharger: Boolean = false, // 수소차 충전소 여부
        var restaurantOpenTime: String? = null, // 식당 영업시간
        var naverRating: Float? = null, // 네이버평점
) : BaseDateTimeEntity()
