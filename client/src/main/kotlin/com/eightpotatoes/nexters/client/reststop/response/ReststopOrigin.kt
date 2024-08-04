package com.eightpotatoes.nexters.client.reststop.response

import com.eightpotatoes.nexters.core.entity.Reststop
import com.eightpotatoes.nexters.core.util.ReststopUtils

data class ReststopOrigin(
    private var entrpsNm : String? = null,
    private val roadKnd : String? = null,
    private val roadRouteNo : String? = null,
    private val roadRouteNm : String? = null,
    private val roadRouteDrc : String? = null,
    private val latitude : String? = null,
    private val longitude : String? = null,
    private val restAreaType : String? = null,
    private val operOpenHhmm : String? = null,
    private val operCloseHhmm : String? = null,
    private val ocpatAr : String? = null,
    private val prkplceCo : String? = null,
    private val crrpwrkYn : String? = null,
    private val oltYn : String? = null,
    private val lpgYn : String? = null,
    private val elctyYn : String? = null,
    private val busTrnsitYn : String? = null,
    private val shltrYn : String? = null,
    private val toiletYn : String? = null,
    private val parmacyYn : String? = null,
    private val nrsgYn : String? = null,
    private val shopYn : String? = null,
    private val rstrtYn : String? = null,
    private val etcCvnt : String? = null,
    private val rprsntvRstrt : String? = null,
    private val phoneNumber : String? = null,
    private val referenceDate : String? = null,
    private val insttCode : String? = null
) {

    fun toReststop(): Reststop {
        var destinationDirection = ""

        if (entrpsNm != null) {
            val (processedName, extractDirection) = ReststopUtils.processReststopName(entrpsNm!!)
            entrpsNm = processedName
            destinationDirection = extractDirection
        }
        return Reststop(
                name = entrpsNm ?: "",
                roadKind = roadKnd ?: "",
                roadRouteNo = roadRouteNo ?: "",
                roadRouteName = roadRouteNm ?: "",
                roadRouteDirection = roadRouteDrc ?: "",
                destinationDirection = destinationDirection,
                reststopType = restAreaType ?: "",
                latitude = latitude!!.toFloat(),
                longitude = longitude!!.toFloat(),
                openTime = operOpenHhmm ?: "",
                closeTime = operCloseHhmm ?: "",
                phoneNumber = phoneNumber ?: "",
                hasGasStation = oltYn == "Y",
                hasLPGChargingStation = lpgYn == "Y",
                hasElectricChargingStation = elctyYn == "Y",
                hasPharmacy = parmacyYn == "Y",
                hasRestaurant = rstrtYn == "Y",
                hasNursingRoom = nrsgYn == "Y",
                hasToilet = toiletYn == "Y"
        )
    }
}
