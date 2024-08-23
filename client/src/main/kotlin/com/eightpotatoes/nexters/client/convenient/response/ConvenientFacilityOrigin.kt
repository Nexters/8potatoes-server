package com.eightpotatoes.nexters.client.convenient.response

import com.eightpotatoes.nexters.core.entity.ConvenientFacility

data class ConvenientFacilityOrigin(
    private val pageNo: String? = null,
    private val numOfRows: String? = null,
    private val stdRestCd: String? = null,
    private val stdRestNm: String? = null,
    private val stime: String? = null,
    private val etime: String? = null,
    private val redId: String? = null,
    private val redDtime: String? = null,
    private val lsttmAltrUser: String? = null,
    private val lsttmAltrDttm: String? = null,
    private val svarAddr: String? = null,
    private val routeCd: String? = null,
    private val routeNm: String? = null,
    private val psCode: String? = null,
    val psName: String? = null,
    private val psDesc: String? = null,

    ) {

    fun toConvenientFacility(imageUrl: String): ConvenientFacility {
        return ConvenientFacility(
            imageUrl = imageUrl,
            standardCode = stdRestCd ?: "",
            name = psName ?: "",
            code = psCode ?: "",
            description = psDesc ?: "",
            startTime = stime ?: "",
            endTime = etime ?: "",
            address = svarAddr ?: "",
            routeCode = routeCd ?: "",
            routeName = routeNm ?: "",
        )
    }
}
