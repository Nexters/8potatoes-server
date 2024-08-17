package com.eightpotatoes.nexters.core.util

import com.eightpotatoes.nexters.core.model.Location
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object LocationUtils {

    fun parseLocation(location: String): Location {
        val (lat, lon) = location.split(",").map { it.toDouble() }
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

    // 중간 구역 계산 함수 (버퍼를 적용)
    fun calculateMiddleZone(from: Location, to: Location): Pair<Location, Location> {
        // 전체 거리 계산
        val totalDistance = calculateDistance(from, to)
        // 전체 거리의 20%를 버퍼로 계산
        val bufferDistance = totalDistance * 0.2

        // 중간 지점 계산
        val midPoint = calculateMidPoint(from, to)

        // 중간 지점에서 위도와 경도에 각각 버퍼를 적용한 좌표 계산
        val bufferLat = calculateLatitudeOffset(midPoint.latitude, bufferDistance)
        val bufferLong = calculateLongitudeOffset(midPoint.latitude, bufferDistance)

        // 중간 구역의 상하좌우 포인트 계산
        val zoneStart = Location(midPoint.latitude - bufferLat, midPoint.longitude - bufferLong) // 좌하단
        val zoneEnd = Location(midPoint.latitude + bufferLat, midPoint.longitude + bufferLong) // 우상단

        return Pair(zoneStart, zoneEnd)
    }

    // 위도에 대한 오프셋 계산 함수 (거리 -> 위도 차이 계산)
    private fun calculateLatitudeOffset(latitude: Double, distance: Double): Double {
        // 지구 반경 (단위: km)
        val earthRadius = 6371.0
        // 위도 차이를 계산
        return Math.toDegrees(distance / earthRadius)
    }

    // 경도에 대한 오프셋 계산 함수 (거리 -> 경도 차이 계산)
    private fun calculateLongitudeOffset(latitude: Double, distance: Double): Double {
        // 지구 반경 (단위: km)
        val earthRadius = 6371.0
        // 경도 차이를 계산 (위도의 코사인 값을 반영)
        return Math.toDegrees(distance / (earthRadius * cos(Math.toRadians(latitude))))
    }

    // 두 좌표 간의 거리 계산 함수 (하버사인 공식)
    fun calculateDistance(from: Location, to: Location): Double {
        val earthRadius = 6371.0 // 지구 반경 (단위: km)

        val dLat = Math.toRadians(to.latitude - from.latitude)
        val dLong = Math.toRadians(to.longitude - from.longitude)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(from.latitude)) * cos(Math.toRadians(to.latitude)) *
                sin(dLong / 2) * sin(dLong / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c
    }
}
