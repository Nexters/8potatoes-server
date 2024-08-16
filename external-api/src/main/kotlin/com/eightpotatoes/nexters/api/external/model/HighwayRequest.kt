package com.eightpotatoes.nexters.api.external.model

data class HighwayRequest(
    val highways: Map<String, List<List<List<Double>>>>
)
