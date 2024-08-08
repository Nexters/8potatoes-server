package com.eightpotatoes.nexters.core.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object ReststopUtils {
    fun processGasStationName(name: String): String {
        // "주유소"로 끝나는 경우, "주유소"를 "휴게소"로 대체
        return if (name.endsWith("주유소")) {
            name.removeSuffix("주유소").plus("휴게소")
        } else {
            name
        }
    }

    fun isRestaurantOpen(openingHours: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val times = openingHours.split(" ~ ")
        val openingTime = LocalTime.parse(times[0], formatter)
        val closingTime = LocalTime.parse(times[1], formatter)
        val currentTime = LocalTime.now()

        return if (closingTime.isBefore(openingTime)) {
            // Handling overnight open hours, e.g., "23:00 ~ 05:00"
            currentTime.isAfter(openingTime) || currentTime.isBefore(closingTime)
        } else {
            currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)
        }
    }
}