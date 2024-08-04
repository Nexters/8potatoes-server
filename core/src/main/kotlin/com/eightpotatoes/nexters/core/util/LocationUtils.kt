package com.eightpotatoes.nexters.core.util

object LocationUtils {

    fun determineDirection(fromLat: Double, toLat: Double): String {
        return if (toLat > fromLat) {
            "상행"
        } else {
            "하행"
        }
    }
}