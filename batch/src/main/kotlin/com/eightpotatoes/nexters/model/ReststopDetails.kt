package com.eightpotatoes.nexters.model

import com.eightpotatoes.nexters.entity.Brand
import com.eightpotatoes.nexters.entity.Menu

data class ReststopDetails(
    val name: String,
    val address: String,
    val isOperating: Boolean,
    val operatingHours: String,
    val fuelInfo: String,
    val naverRating: Float,
    val foodMenus: List<Menu>,
    val brands: List<Brand>
)
