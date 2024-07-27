package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Direction
import org.springframework.data.jpa.repository.JpaRepository

interface DirectionRepository : JpaRepository<Direction, Int>