package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Reststop
import org.springframework.data.jpa.repository.JpaRepository

interface ReststopRepository : JpaRepository<Reststop, Int> {
}
