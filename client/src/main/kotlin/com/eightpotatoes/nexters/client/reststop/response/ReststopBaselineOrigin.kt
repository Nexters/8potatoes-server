package com.eightpotatoes.nexters.client.reststop.response

data class ReststopBaselineOrigin(
    val unitName : String? = null, // 휴게소명
    val routeName : String? = null, // 노선명
    val xValue : String? = null, // X좌표값
    val yValue : String? = null, // Y좌표값
    val numOfRows : String? = null, // 한 페이지당 출력건수
    val pageNo : String? = null, // 출력 페이지번호
    val stdRestCd : String? = null, // 휴게소/주유소코드
    val serviceAreaCode : String? = null, // 영업부대시설코드
    val unitCode : String? = null, // 휴게소코드
    val routeNo : String? = null, // 노선코드
    val pageSize : String? = null, // 전체 페이지 수
    val code : String? = null, // 결과
    val message : String? = null, // 결과 메시지
    val count : String? = null, // 전체 결과 수
)