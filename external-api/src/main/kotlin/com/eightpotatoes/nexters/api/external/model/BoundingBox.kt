package com.eightpotatoes.nexters.api.external.model

import com.eightpotatoes.nexters.core.model.Location

data class BoundingBox(
    val topLeft: Location,
    val topRight: Location,
    val bottomLeft: Location,
    val bottomRight: Location,
    val buffer: Double = 0.001  // 버퍼 값 추가
) {
    fun contains(location: Location): Boolean {
        // 버퍼를 적용한 박스 구간 안에 위치가 포함되는지 확인하는 로직
        val expandedTopLeftLat = topLeft.latitude + buffer
        val expandedBottomLeftLat = bottomLeft.latitude - buffer
        val expandedTopRightLon = topRight.longitude + buffer
        val expandedTopLeftLon = topLeft.longitude - buffer

        val latInRange = location.latitude in expandedBottomLeftLat..expandedTopLeftLat
        val lonInRange = location.longitude in expandedTopLeftLon..expandedTopRightLon
        return latInRange && lonInRange
    }
}
