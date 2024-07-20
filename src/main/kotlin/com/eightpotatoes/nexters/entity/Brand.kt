package com.eightpotatoes.nexters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Brand")
data class Brand(
    @Id val id: Int,
    val name: String,
    val thumbnailUrl: String,
)