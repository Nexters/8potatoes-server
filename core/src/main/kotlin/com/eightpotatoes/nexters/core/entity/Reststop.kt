package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.*

@Table(name = "reststop")
@Entity
class Reststop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String, // Name(서울만남(부산)휴게소)
    var roadRouteNo: String, // 0010
    var roadRouteName: String, // 경부선
    var roadRouteDirection: String, // 상행,하행
    var latitude: Float, // yValue 위도(37.459939)
    var longitude: Float, // xValue 경도(127.042514)
    var phoneNumber: String, // 전화번호
    var referenceDate: String, // 데이터기준일자
    var representativeFoodName: String, // 대표음식명
    var reststopType: String, // 휴게소 유형 : 일반/화물차/간이
    val standardCode: String, // stdRestCd(000001) : 표준 휴게소/주유소 공통 코드
    var serviceAreaCode: String, // serviceAreaCode(A00001) : 서비스 구역 코드
    var address: String? = null, // 주소

    var smallCarSpace: Int? = null, // cocrPrkgTrcn(30) : 소형차 주차면수
    var largeCarSpace: Int? = null, // fscarPrkgTrcn(10) : 대형차 주차면수
    var disabledPersonSpace: Int? = null, // dspnPrkgTrcn(2) : 장애인 주차면수
    var totalSpace: Int? = null, // 총 주차면수
    var gasolinePrice: String? = null, // 휘발유 가격
    var dieselPrice: String? = null, // 경유 가격
    var lpgPrice: String? = null, // LPG 가격

    var hasElectricCharger: Boolean = false, // 전기차 충전소 여부
    var hasHydrogenCharger: Boolean = false, // 수소차 충전소 여부
    var restaurantOpenTime: String? = null, // 식당 영업시간
    var naverRating: Float? = null, // 네이버평점
) : BaseDateTimeEntity()
