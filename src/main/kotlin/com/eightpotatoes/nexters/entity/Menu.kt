package com.eightpotatoes.nexters.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Table(name = "menu")
@Entity
class Menu(
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
