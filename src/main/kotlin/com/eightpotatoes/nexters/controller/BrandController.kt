package com.eightpotatoes.nexters.controller

import com.eightpotatoes.nexters.service.BrandService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BrandController(val brandService: BrandService) {

    @GetMapping(value = ["/full-import/brands"])
    suspend fun fullImportBrand() {
        brandService.fullImportBrand()
    }
}
