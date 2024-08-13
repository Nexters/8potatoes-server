package com.eightpotatoes.nexters.core.util

import com.eightpotatoes.nexters.core.model.Location

object LocationUtils {

    fun parseLocation(location: String): Location {
        val (lat, lon) = location.split(",").map { it.toFloat() }
        return Location(lat, lon)
    }

    fun determineDirection(from: Location, to: Location): String {
        return if (to.latitude > from.latitude) {
            "상행"
        } else {
            "하행"
        }
    }

    // 중간 지점 계산 함수
    fun calculateMidPoint(from: Location, to: Location): Location {
        val midLat = (from.latitude + to.latitude) / 2
        val midLong = (from.longitude + to.longitude) / 2

        return Location(midLat, midLong)
    }

    // 중간 구역 계산 함수
    fun calculateMiddleZone(from: Location, to: Location): Pair<Location, Location> {
        val midLat = (from.latitude + to.latitude) / 2
        val midLong = (from.longitude + to.longitude) / 2

        val zoneStartLat = from.latitude + (midLat - from.latitude) * 0.3
        val zoneEndLat = from.latitude + (midLat - from.latitude) * 0.7

        val zoneStartLong = from.longitude + (midLong - from.longitude) * 0.3
        val zoneEndLong = from.longitude + (midLong - from.longitude) * 0.7

        return Pair(Location(zoneStartLat.toFloat(), zoneStartLong.toFloat()), Location(zoneEndLat.toFloat(), zoneEndLong.toFloat()))
    }
}