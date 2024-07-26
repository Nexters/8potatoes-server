package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.ReststopBrand
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ReststopBrandRepository : ReactiveCrudRepository<ReststopBrand, Int> {
    fun findByReststopId(reststopId: Int): Flux<ReststopBrand>
}
