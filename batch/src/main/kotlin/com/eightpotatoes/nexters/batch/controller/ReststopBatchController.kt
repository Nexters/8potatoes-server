package com.eightpotatoes.nexters.batch.controller

import com.eightpotatoes.nexters.client.reststop.ReststopService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReststopBatchController(private val reststopService: ReststopService) {

    @PostMapping("/batch/reststops")
    suspend fun upsertReststops() = coroutineScope {
        reststopService.fullImportReststop()
    }
}
