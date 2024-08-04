package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Int>, MenuRepositoryCustom {
    fun findByReststopId(reststopId: String?): List<Menu>
}
