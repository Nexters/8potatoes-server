package com.eightpotatoes.nexters.batch.convenient

import com.eightpotatoes.nexters.client.convenient.ConvenientFacilityService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ConvenientFacilityBatchController(private val convenientFacilityService: ConvenientFacilityService) {

    @PostMapping("/batch/convenient-facilities/info")
    suspend fun upsertConvenientInfo() {
        convenientFacilityService.fullImportBrand()
    }
}
