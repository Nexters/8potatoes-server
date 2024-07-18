package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Reststop
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ReststopRepository : ReactiveCrudRepository<Reststop, Int> {
    fun findByDirectionId(directionId: Int): Flux<Reststop>
    fun findByCode(reststopCode: String): Flux<Reststop>
}
