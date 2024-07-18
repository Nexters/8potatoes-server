package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Highway
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface HighwayRepository : ReactiveCrudRepository<Highway, Int>