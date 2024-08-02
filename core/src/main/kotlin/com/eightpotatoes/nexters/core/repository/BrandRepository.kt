package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<Brand, Long>