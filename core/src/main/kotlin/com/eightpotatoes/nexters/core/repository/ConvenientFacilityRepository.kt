package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.ConvenientFacility
import org.springframework.data.jpa.repository.JpaRepository

interface ConvenientFacilityRepository : JpaRepository<ConvenientFacility, Long> {
    fun findByStandardCode(standardCode: String): List<ConvenientFacility>
}