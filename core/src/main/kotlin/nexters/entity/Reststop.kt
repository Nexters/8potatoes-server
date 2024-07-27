package com.eightpotatoes.nexters.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "rest_stop")
@Entity
class Reststop(
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