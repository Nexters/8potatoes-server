package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ReststopAdditionalOrigin(
    val cocrPrkgTrcn: String? = null, // 소형차주차대수
    val fscarPrkgTrcn: String? = null, // 대형차주차대수
    val dspnPrkgTrcn: String? = null, // 장애인주차대수
    val gudClssNm: String? = null, // 상하행구분명
    @JsonProperty("svarCd")
    val standardCode: String? = null, // 휴게소/주유소코드 KEY
    // unuseful
    val rprsTelNo: String? = null, // 대표전화번호
    val svarAddr: String? = null, // 휴게소주소
    @JsonProperty("svarNm")
    val name: String? = null, // 휴게소명
    val bsopAdtnlFcltCd: String? = null, // 영업부대시설코드
    val routeCd: String? = null, // 노선코드
    val routeNm: String? = null, // 노선명
    val hdqrCd: String? = null, // 본부코드
    val hdqrNm: String? = null, // 본부명
    val mtnofCd: String? = null, // 지사코드
    val mtnofNm: String? = null, // 지사명
    val svarGsstClssCd: String? = null, // 휴게소/주유소구분코드
    val svarGsstClssNm: String? = null, // 휴게소/주유소구분명
    val gudClssCd: String? = null, // 상하행구분코드
    val pstnoCd: String? = null, // 우편번호
)
