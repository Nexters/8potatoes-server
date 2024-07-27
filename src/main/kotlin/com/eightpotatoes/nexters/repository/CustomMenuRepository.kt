package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Menu

interface CustomMenuRepository {
    suspend fun upsertMenu(menu: Menu): Menu
}
