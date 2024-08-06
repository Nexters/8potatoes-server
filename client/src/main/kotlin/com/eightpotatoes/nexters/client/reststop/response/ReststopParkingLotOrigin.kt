package com.eightpotatoes.nexters.client.reststop.response

data class ReststopParkingLotOrigin(
    val svarCd : String? = null, // 휴게소코드
    val svarNm : String? = null, // 휴게소명
    val routeCd : String? = null, // 노선코드
    val routeNm : String? = null, // 노선명
    val hdqrCd : String? = null, // 본부코드
    val hdqrNm : String? = null, // 본부명
    val mtnofCd : String? = null, // 지사코드
    val mtnofNm : String? = null, // 지사명
    val svarGsstClssCd : String? = null, // 휴게소/주유소구분코드
    val svarGsstClssNm : String? = null, // 휴게소/주유소구분명
    val gudClssCd : String? = null, // 상하행구분코드
    val gudClssNm : String? = null, // 상하행구분명
    val pstnoCd : String? = null, // 우편번호
    val svarAddr : String? = null, // 휴게소주소
    val cocrPrkgTrcn : String? = null, // 소형차주차대수
    val fscarPrkgTrcn : String? = null, // 대형차주차대수
    val dspnPrkgTrcn : String? = null, // 장애인주차대수
    val bsopAdtnlFcltCd : String? = null, // 영업부대시설코드
    val rprsTelNo : String? = null, // 대표전화번호
    val code : String? = null, // 결과
    val message : String? = null, // 결과 메시지
    val count : String? = null, // 전체 결과 수
)