package com.eightpotatoes.nexters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Direction")
data class Direction(
    @Id val id: Int,
    val name: String,
    val highwayId: Int,
)