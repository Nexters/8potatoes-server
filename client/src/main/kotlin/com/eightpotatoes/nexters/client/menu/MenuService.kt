package com.eightpotatoes.nexters.client.menu

import com.eightpotatoes.nexters.core.entity.Menu
import com.eightpotatoes.nexters.core.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    suspend fun upsertMenu(menu: Menu) {
        return menuRepository.upsert(menu)
    }
}
