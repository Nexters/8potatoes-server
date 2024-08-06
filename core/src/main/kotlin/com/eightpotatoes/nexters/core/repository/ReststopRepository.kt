package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Reststop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ReststopRepository : JpaRepository<Reststop, Long> {
    fun findByName(name: String):  Reststop?

//    @Query("SELECT r FROM Reststop r WHERE r.roadRouteName IN :roadNameList AND (r.roadRouteDirection = :direction OR r.roadRouteDirection = '양방향')")
//    fun findByRoadRouteNameAndDirection(
//        @Param("roadNameList") roadNameList: List<String>,
//        @Param("direction") direction: String
//    ): List<Reststop>
}