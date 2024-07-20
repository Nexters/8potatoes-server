package com.eightpotatoes.nexters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Menu")
data class Menu(
    @Id val id: Int,
    val name: String,
    val price: Int,
    val isRecommended: Boolean,
    val isPremium: Boolean,
    val isBestFood: Boolean,
    val reststopId: Int,
)