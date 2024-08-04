package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.*

@Table(name = "menu")
@Entity
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val foodSeq: String,
    val name: String,
    val price: Int,
    val description: String?,
    val isRecommended: Boolean,
    val isPremium: Boolean,
    val isBestFood: Boolean,
    val reststopId: String,
) : BaseDateTimeEntity()
