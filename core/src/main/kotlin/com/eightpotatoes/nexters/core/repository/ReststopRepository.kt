package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Reststop
import org.springframework.data.jpa.repository.JpaRepository

interface ReststopRepository : JpaRepository<Reststop, Int> {
}