package com.eightpotatoes.nexters.client.reststop

import com.eightpotatoes.nexters.core.entity.Reststop
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
    suspend fun fullImportReststopInfo() {
        // 휴게소 기준 정보 현황 Import
        val reststopBaselineList = reststopClient.importReststopBaseline()
        // 주차대수, 도로노선방향 정보 Import
        val reststopParkingLotList = reststopClient.importReststopParkingLot()
        // 주유소 가격 정보 Import
        val gasStationOriginList = reststopClient.importReststopOilPrice()
        // 전기차/수소차 충전소 정보 import
        val chargingStationOriginList = reststopClient.importChargingStation()

        val parkingLotMap = reststopParkingLotList.associateBy { it.svarCd }
        val gasStationMap = gasStationOriginList.associateBy { it.serviceAreaCode }
        val chargingStationMap = chargingStationOriginList.associateBy { it.facilityName + it.facilityType }

        // 데이터 조합
        val reststopList = reststopBaselineList.mapNotNull { baseline ->
            val standardCode = baseline.stdRestCd ?: return@mapNotNull null
            val name = baseline.unitName ?: return@mapNotNull null
            val routeName = baseline.routeName ?: return@mapNotNull null
            val routeNo = baseline.routeNo ?: return@mapNotNull null
            val longitude = baseline.xValue?.toFloatOrNull() ?: return@mapNotNull null
            val latitude = baseline.yValue?.toFloatOrNull() ?: return@mapNotNull null
            val serviceAreaCode = baseline.serviceAreaCode ?: return@mapNotNull null

            val parkingLot = parkingLotMap[standardCode]
            val gasStation = gasStationMap[serviceAreaCode]
            val chargingStation = chargingStationMap[name]

            Reststop(
                standardCode = standardCode,
                name = name,
                code = baseline.unitCode ?: "",
                routeName = routeName,
                routeNo = routeNo,
                longitude = longitude,
                latitude = latitude,
                serviceAreaCode = serviceAreaCode,
                smallCarSpace = parkingLot?.cocrPrkgTrcn?.toIntOrNull() ?: 0,
                largeCarSpace = parkingLot?.fscarPrkgTrcn?.toIntOrNull() ?: 0,
                disabledPersonSpace = parkingLot?.dspnPrkgTrcn?.toIntOrNull() ?: 0,
                routeDirection = gasStation?.direction ?: "",
                gasolinePrice = gasStation?.gasolinePrice,
                dieselPrice = gasStation?.diselPrice,
                lpgPrice = gasStation?.lpgPrice,
                address = gasStation?.svarAddr ?: "",
                phoneNumber = parkingLot?.rprsTelNo ?: "",
                hasElectricCharger = chargingStation?.electricChargingStation == "O",
                hasHydrogenCharger = chargingStation?.hydrogenChargingStation == "O",
                restaurantOpenTime = null,  // 추후 추가
                naverRating = null,  // 추후 추가
            )
        }

        reststopRepository.saveAll(reststopList)
    }

    @Transactional
    suspend fun importReststopOilPrice() {
        val gasStationOrigins = reststopClient.importReststopOilPrice()
        if (gasStationOrigins.isEmpty()) return
        gasStationOrigins
            .mapNotNull { it.serviceAreaName?.let { name -> name to it } }
            .forEach { (name, gasStationOrigin) ->
                val processedName = ReststopUtils.processGasStationName(name)
                reststopRepository.findByName(processedName)?.apply {
                    gasStationOrigin.gasolinePrice?.let { gasolinePrice = it }
                    gasStationOrigin.diselPrice?.let { dieselPrice = it }
                    gasStationOrigin.lpgPrice?.let { lpgPrice = it }
                    gasStationOrigin.svarAddr?.let { address = it }
                    gasStationOrigin.telNo?.let { phoneNumber = it }
                    reststopRepository.save(this)
                }
            }
    }

    @Transactional
    suspend fun importNaverRating() {
        val reststops = reststopRepository.findAll()
        reststops.forEach { reststop ->
            // TODO Scraping code 연동 변경
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
