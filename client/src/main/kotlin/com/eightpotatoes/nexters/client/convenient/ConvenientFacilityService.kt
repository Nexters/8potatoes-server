package com.eightpotatoes.nexters.client.convenient

import com.eightpotatoes.nexters.core.repository.ConvenientFacilityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConvenientFacilityService(
    private val convenientFacilityClient: ConvenientFacilityClient,
    private val convenientFacilityRepository: ConvenientFacilityRepository
) {

    @Transactional // TODO: 외부 요청 분리
    suspend fun fullImportBrand() {
        val fullImportConvenientFacilities = convenientFacilityClient.fullImportConvenientFacilities();

        convenientFacilityRepository.saveAll(fullImportConvenientFacilities)
    }
}
