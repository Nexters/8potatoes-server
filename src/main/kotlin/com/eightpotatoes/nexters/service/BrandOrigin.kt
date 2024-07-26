package com.eightpotatoes.nexters.service

import com.eightpotatoes.nexters.entity.Brand

data class BrandOrigin(
    private val pageNo: String? = null,
    private val numOfRows: String? = null,
    private val stdRestCd: String? = null,
    private val stdRestNm: String? = null,
    private val brdCode: String? = null,
    private val brdName: String? = null,
    private val brdDesc: String? = null,
    private val stime: String? = null,
    private val etime: String? = null,
    private val redId: String? = null,
    private val redDtime: String? = null,
    private val lsttmAltrUser: String? = null,
    private val lsttmAltrDttm: String? = null,
    private val svarAddr: String? = null,
    private val routeCd: String? = null,
    private val routeNm: String? = null) {

    fun toBrand(): Brand {
        return Brand(
                brandCode = brdCode ?: "",
                name = brdName ?: "",
                description = brdDesc ?: "",
                restStopCode = stdRestCd ?: "",
                restStopName = stdRestNm ?: "",
                routeName = routeNm ?: "",
                address = svarAddr ?: "",
                thumbnailUrl = ""
        )
    }
}
