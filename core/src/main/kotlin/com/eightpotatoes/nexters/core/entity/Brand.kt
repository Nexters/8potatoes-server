package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id

@Table(name = "brand")
@Entity
class Brand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val brandCode: String,
    val name: String,
    val description: String,
    val reststopCode: String,
    val reststopName: String,
    val routeName: String,
    val address: String,
    val thumbnailUrl: String
) : BaseDateTimeEntity()
