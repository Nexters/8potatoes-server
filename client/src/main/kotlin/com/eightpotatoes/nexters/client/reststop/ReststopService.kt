package com.eightpotatoes.nexters.client.reststop

import com.eightpotatoes.nexters.core.repository.ReststopRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReststopService(
    private val reststopClient: ReststopClient,
    private val reststopRepository: ReststopRepository
) {

    @Transactional
    suspend fun fullImportReststop() {
        val reststops = reststopClient.fullImportReststop();

        reststopRepository.saveAll(reststops)
    }
}
