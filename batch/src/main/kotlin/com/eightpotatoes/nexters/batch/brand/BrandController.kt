package com.eightpotatoes.nexters.batch.brand

import com.eightpotatoes.nexters.client.brand.BrandService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BrandController(val brandService: BrandService) {

    @PostMapping("/batch/brands/info")
    suspend fun fullImportBrand() {
        brandService.fullImportBrand()
    }
}
