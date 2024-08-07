package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ReststopOrigin(
    @JsonProperty("entrpsNm")
    val name: String? = null, // 휴게소명 : 횡성(강릉)
    val roadRouteNo: String? = null, // 도로번호 : 1
    @JsonProperty("roadRouteNm")
    val roadRouteName: String? = null, // 도로명 : 경부선
    @JsonProperty("roadRouteDrc")
    val roadRouteDirection: String? = null, // 도로방향 : 상행/하행
    val latitude: String? = null, // 위도 : 37.500000
    val longitude: String? = null, // 경도 : 127.000000
    val phoneNumber: String? = null, // 전화번호 : 033-340-8000
    val referenceDate: String? = null, // 데이터기준일자 : 2021-07-01
    @JsonProperty("rprsntvRstrt")
    val representativeFoodName: String? = null, // 대표음식명 : 횡성더덕설렁탕
    @JsonProperty("restAreaType")
    val reststopType: String? = null, // 휴게소 유형 : 일반/화물차/간이

    // unuseful
    val ocpatAr: String? = null, // 점유면적 : 1000
    val prkplceCo: String? = null, // 주차면수 : 100
    val crrpwrkYn: String? = null, // 경정비가능여부 : Y/N
    val oltYn: String? = null, // 주유소유무 : Y/N
    val lpgYn: String? = null, // LPG충전소유무 : Y/N
    val elctyYn: String? = null, // 전기차충전소유무 : Y/N
    val busTrnsitYn: String? = null, // 버스환승가능여부 : Y/N
    val shltrYn: String? = null, // 휴게소내쉼터유무 : Y/N
    val parmacyYn: String? = null, // 약국유무 : Y/N
    val nrsgYn: String? = null, // 수유실유무 : Y/N
    val shopYn: String? = null, // 매점유무 : Y/N
    val rstrtYn: String? = null, // 음식점유무 : Y/N
    // unuseful : 모두 Y
    val toiletYn: String? = null, // 화장실유무 : Y/N
    // unuseful : 데이터 없음
    val etcCvnt: String? = null, // 기타편의시설 : 없음
    val insttCode: String? = null, // 제공기관코드 : 없음
    // unuseful : 전부 고속국도
    val roadKnd: String? = null, // 도로종류 : 고속국도
    // unuseful : 전부 00:00 ~ 23:59
    val operOpenHhmm: String? = null, // 운영시작시간 : 00:00
    val operCloseHhmm: String? = null // 운영종료시간 : 23:59
)