package com.eightpotatoes.nexters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("ReststopBrand")
data class ReststopBrand(
    @Id val id: Int,
    val reststopId: Int,
    val brandId: Int,
)