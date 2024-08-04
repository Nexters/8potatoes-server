package com.eightpotatoes.nexters.batch.reststop

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

    @PostMapping("/batch/reststops/code")
    suspend fun updateReststopsCode() = coroutineScope {
        reststopService.importReststopServiceCode()
    }

    @PostMapping("/batch/reststops/oil")
    suspend fun upsertReststopsOilPrice() = coroutineScope {
        reststopService.importReststopOilPrice()
    }

    @PostMapping("/batch/reststops/rating")
    suspend fun updateNaverRating() = coroutineScope {
        reststopService.importNaverRating()
    }
}
