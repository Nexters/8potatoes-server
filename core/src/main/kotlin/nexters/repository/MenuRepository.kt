package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Menu
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface MenuRepository : ReactiveCrudRepository<Menu, Long>, CustomMenuRepository
