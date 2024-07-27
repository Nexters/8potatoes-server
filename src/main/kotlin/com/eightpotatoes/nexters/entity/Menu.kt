package com.eightpotatoes.nexters.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("Menu")
data class Menu(
    @Id val id: Int,
    val foodSeq: String,
    val name: String,
    val price: Int,
    val description: String?,
    val isRecommended: Boolean,
    val isPremium: Boolean,
    val isBestFood: Boolean,
    val reststopId: Int,
    @CreatedDate
    val createdAt: LocalDateTime? = null,
    @LastModifiedDate
    val updatedAt: LocalDateTime? = null
)