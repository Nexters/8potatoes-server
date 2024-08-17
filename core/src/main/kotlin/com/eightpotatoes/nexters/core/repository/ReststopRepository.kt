package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Reststop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ReststopRepository : JpaRepository<Reststop, Long> {
    fun findByStandardCode(standardCode: String): Reststop?

    @Query("SELECT r FROM Reststop r WHERE (r.roadRouteDirection = :direction OR r.roadRouteDirection = '양방향')")
    fun findByDirection(
        @Param("direction") direction: String
    ): List<Reststop>
}
