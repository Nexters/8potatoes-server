package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Direction
import org.springframework.data.jpa.repository.JpaRepository
import reactor.core.publisher.Flux

interface DirectionRepository : JpaRepository<Direction, Int>
