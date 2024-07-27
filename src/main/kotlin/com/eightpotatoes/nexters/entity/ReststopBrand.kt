package com.eightpotatoes.nexters.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id

@Table(name = "rest_stop_brand")
@Entity
class ReststopBrand(
    @Id val id: Int,
    val reststopId: Int,
    val brandId: Int,
)