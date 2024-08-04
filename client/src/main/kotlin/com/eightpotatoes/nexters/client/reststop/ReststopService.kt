package com.eightpotatoes.nexters.client.reststop

import com.eightpotatoes.nexters.core.repository.ReststopRepository
import com.eightpotatoes.nexters.core.util.ReststopUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
class ReststopService(
    private val reststopClient: ReststopClient,
    private val reststopRepository: ReststopRepository
) {

    @Transactional
    suspend fun fullImportReststop() {
        val reststops = reststopClient.fullImportReststop()

        reststopRepository.saveAll(reststops)
    }

    @Transactional
    suspend fun importReststopOilPrice() {
        val gasStationOrigins = reststopClient.importReststopOilPrice()
        if (gasStationOrigins.isEmpty()) return
        gasStationOrigins
            .mapNotNull { it.serviceAreaName?.let { name -> name to it } }
            .forEach { (name, gasStationOrigin) ->
                val (processedName, direction) = ReststopUtils.processGasStationName(name)
                reststopRepository.findByNameAndDestinationDirection(processedName, direction)?.apply {
                    gasStationOrigin.gasolinePrice?.let { gasolinePrice = it }
                    gasStationOrigin.diselPrice?.let { dieselPrice = it }
                    gasStationOrigin.lpgPrice?.let { lpgPrice = it }
                    gasStationOrigin.routeCode?.let { routeCode = it }
                    gasStationOrigin.serviceAreaCode2?.let { gasStationCode = it }
                    reststopRepository.save(this)
                }
            }
    }

    @Transactional
    suspend fun importReststopServiceCode() {

        val reststopCodeOrigins = reststopClient.importReststopServiceCode()
        reststopCodeOrigins
            .mapNotNull { it.serviceAreaName?.let { name -> name to it.serviceAreaCode2 } }
            .forEach { (name, serviceAreaCode2) ->
                val (processedName, direction)= ReststopUtils.processReststopName(name, false)
                reststopRepository.findByNameAndDestinationDirection(processedName, direction)?.let { reststop ->
                    reststop.reststopCode = serviceAreaCode2
                    println(reststop.toString())
                    reststopRepository.save(reststop)
                }
            }
    }

    @Transactional
    suspend fun importNaverRating() {
        val reststops = reststopRepository.findAll()
        reststops.forEach { reststop ->
            // TODO 스크래핑 코드 연동으로 변경
            reststop.naverRating = generateRandomFloatInRange()
            reststopRepository.save(reststop)
        }
    }

    private fun generateRandomFloatInRange(): Float {
        val start = 10
        val end = 50
        val randomValue = Random.nextInt(start, end + 1)
        return randomValue / 10.0f
    }
}
