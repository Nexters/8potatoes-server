package com.eightpotatoes.nexters.controller

import com.eightpotatoes.nexters.service.ReststopFoodService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReststopFoodController(private val reststopFoodService: ReststopFoodService) {

    @PostMapping("/api/reststop/foods")
    suspend fun upsertReststopFoods() = coroutineScope {
        reststopFoodService.callAndUpsertAllFoodPages()
    }
}
