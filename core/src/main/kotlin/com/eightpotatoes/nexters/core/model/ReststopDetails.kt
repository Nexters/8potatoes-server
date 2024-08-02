package com.eightpotatoes.nexters.core.model

import com.eightpotatoes.nexters.core.entity.Brand
import com.eightpotatoes.nexters.core.entity.Menu

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
