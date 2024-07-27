package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id

@Table(name = "direction")
@Entity
class Direction(
    @Id val id: Int,
    val name: String,
    val highwayId: Int,
)