package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id

@Table(name = "highway")
@Entity
class Highway(
    @Id val id: Int,
    val name: String,
)