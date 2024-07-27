package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Int>
