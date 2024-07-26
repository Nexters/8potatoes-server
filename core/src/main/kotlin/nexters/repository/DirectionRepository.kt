package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Direction
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface DirectionRepository : ReactiveCrudRepository<Direction, Int> {
    fun findByName(name: String): Flux<Direction>
}
