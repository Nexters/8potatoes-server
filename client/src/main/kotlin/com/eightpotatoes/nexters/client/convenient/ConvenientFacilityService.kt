package com.eightpotatoes.nexters.client.convenient

import com.eightpotatoes.nexters.core.entity.ConvenientFacility
import com.eightpotatoes.nexters.core.repository.ConvenientFacilityRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConvenientFacilityService(
    private val convenientFacilityClient: ConvenientFacilityClient,
    private val convenientFacilityRepository: ConvenientFacilityRepository,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${cloud.aws.s3.endpoint}")
    private val baseUrl: String? = null

    @Transactional
    suspend fun fullImportBrand() {
        val convenientFacilities = mutableListOf<ConvenientFacility>()
        val convenientFacilityOriginList = convenientFacilityClient.fullImportConvenientFacilities()
        for (convenientFacilityOrigin in convenientFacilityOriginList) {
            val filePath = "Amenities/${convenientFacilityOrigin.psName}.svg"
            val facilityImageUrl = "$baseUrl/$bucket/$filePath"
            convenientFacilities.add(convenientFacilityOrigin.toConvenientFacility(facilityImageUrl))
        }

        convenientFacilityRepository.saveAll(convenientFacilities)
    }
}
