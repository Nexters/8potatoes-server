package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.ReststopBrand
import org.springframework.data.jpa.repository.JpaRepository

interface ReststopBrandRepository : JpaRepository<ReststopBrand, Int> {
}
