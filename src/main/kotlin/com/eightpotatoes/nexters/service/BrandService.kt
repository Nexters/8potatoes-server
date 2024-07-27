package com.eightpotatoes.nexters.service

import com.eightpotatoes.nexters.repository.BrandRepository
import org.springframework.stereotype.Service

@Service
class BrandService(
        private val brandClient: BrandClient,
        private val brandRepository: BrandRepository
) {

    suspend fun fullImportBrand() {
        val brands = brandClient.fullImportBrand()

        brandRepository.saveAll(brands)
    }
}
