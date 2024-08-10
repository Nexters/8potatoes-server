package com.eightpotatoes.nexters.batch.reststop

import com.eightpotatoes.nexters.client.reststop.ReststopService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReststopBatchController(private val reststopService: ReststopService) {

    // 휴게소 정보 업데이트 Batch
    @PostMapping("/batch/reststops/info")
    suspend fun upsertReststopsInfo() = coroutineScope {
        reststopService.fullImportReststopInfos()
    }

    // Naver 휴게소 평점 업데이트 Batch
    @PostMapping("/batch/reststops/rating")
    suspend fun updateNaverRating() = coroutineScope {
        reststopService.importNaverRating()
    }

    // 식당 영업시간 업데이트 batch
    @PostMapping("/batch/reststops/restaurants-hours")
    suspend fun updateRestaurantsHours() = coroutineScope {
        reststopService.updateRestaurantsHours()
    }

}
