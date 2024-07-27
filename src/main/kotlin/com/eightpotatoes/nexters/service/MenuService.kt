package com.eightpotatoes.nexters.service

import com.eightpotatoes.nexters.entity.Menu
import com.eightpotatoes.nexters.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    suspend fun upsertMenu(menu: Menu): Menu {
        return menuRepository.save(menu)
    }
}