package com.eightpotatoes.nexters.core.util

object LocationUtils {
    fun parseCoordinates(coordinate: String): Pair<Double, Double> {
        val parts = coordinate.split(",")
        return Pair(parts[0].toDouble(), parts[1].toDouble())
    }

    fun determineDirection(from: Pair<Double, Double>, to: Pair<Double, Double>): String {
        val (fromLat, fromLon) = from
        val (toLat, toLon) = to

        return if (toLat > fromLat) {
            "상행"
        } else {
            "하행"
        }
    }
}