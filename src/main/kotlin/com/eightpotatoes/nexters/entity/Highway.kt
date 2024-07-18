package com.eightpotatoes.nexters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Highway")
data class Highway(
    @Id val id: Int,
    val name: String,
)