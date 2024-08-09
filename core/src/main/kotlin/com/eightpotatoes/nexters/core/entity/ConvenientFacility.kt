package com.eightpotatoes.nexters.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id

@Table(name = "convenient_facility")
@Entity
class ConvenientFacility(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val standardCode: String,
    val name: String,
    val code: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val address: String,
    val routeCode: String,
    val routeName: String
) : BaseDateTimeEntity()
