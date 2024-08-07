package com.eightpotatoes.nexters.core.util

object ReststopUtils {
    fun processGasStationName(name: String): String {
        // "주유소"로 끝나는 경우, "주유소"를 "휴게소"로 대체
        return if (name.endsWith("주유소")) {
            name.removeSuffix("주유소").plus("휴게소")
        } else {
            name
        }
    }
}