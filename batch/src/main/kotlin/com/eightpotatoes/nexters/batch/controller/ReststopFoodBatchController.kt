package com.eightpotatoes.nexters.batch.controller

import com.eightpotatoes.nexters.client.menu.ReststopFoodService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReststopFoodBatchController(private val reststopFoodService: ReststopFoodService) {

    @PostMapping("/batch/reststop/foods")
    suspend fun upsertReststopFoods() = coroutineScope {
        reststopFoodService.callAndUpsertAllFoodPages()
    }
}
