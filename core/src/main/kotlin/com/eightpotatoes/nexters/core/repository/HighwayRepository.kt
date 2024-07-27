package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Highway
import org.springframework.data.jpa.repository.JpaRepository

interface HighwayRepository : JpaRepository<Highway, Int>