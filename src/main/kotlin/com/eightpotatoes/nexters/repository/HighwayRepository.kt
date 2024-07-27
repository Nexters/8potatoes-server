package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Highway
import org.springframework.data.jpa.repository.JpaRepository

interface HighwayRepository : JpaRepository<Highway, Int>