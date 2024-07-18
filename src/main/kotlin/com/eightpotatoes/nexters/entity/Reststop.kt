package com.eightpotatoes.nexters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Reststop")
data class Reststop(
    @Id val id: Int,
    val name: String,
    val address: String,
    val latitude: Float,
    val longitude: Float,
    val bestMenu: String,
    val totalMenuCount: Int,
    val isOperating: Boolean,
    val operatingHours: String,
    val fuelInfo: String,
    val naverRating: Float,
    val code: String,
    val directionId: Int,
)