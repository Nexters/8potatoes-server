package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Menu

interface MenuRepositoryCustom {
    fun upsert(menu: Menu)
}
