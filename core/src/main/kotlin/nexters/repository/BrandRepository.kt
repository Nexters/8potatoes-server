package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Brand
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface BrandRepository : ReactiveCrudRepository<Brand, Int>
