package com.eightpotatoes.nexters.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeUtils {
    fun formatLocalDateTime(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return dateTime.format(formatter)
    }
}