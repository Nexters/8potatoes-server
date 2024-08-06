package com.eightpotatoes.nexters.core.util

object ReststopUtils {

    fun processReststopName(
        name: String,
        addSuffix: Boolean = true
    ): Pair<String, String> {
        val regex = Regex("\\((.*?)\\)")
        val matchResult = regex.find(name)

        val destinationDirection = matchResult?.groupValues?.get(1) ?: ""
        var processedName = name.replace(regex, "").trim()

        if (addSuffix) {
            processedName += "휴게소"
        }

        return Pair(processedName, destinationDirection)
    }

    fun processGasStationName(
        name: String,
    ): String {
        // 마지막 글자가 "주유소"라면 주유소 제거 후 휴게소 추가
        if (name.endsWith("주유소")) {
            name.substring(0, name.length - 3).plus("휴게소")
        }
        return name
    }
}