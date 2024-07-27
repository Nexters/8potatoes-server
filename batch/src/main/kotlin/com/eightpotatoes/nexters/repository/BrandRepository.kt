package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<Brand, Long>
