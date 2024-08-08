package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id

@Table(name = "reststop_brand")
@Entity
class ReststopBrand(
    @Id val id: Int,
    val reststopCode: Int,
    val brandId: Int,
)