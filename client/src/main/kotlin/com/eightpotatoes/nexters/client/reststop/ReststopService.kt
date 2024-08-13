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
    suspend fun fullImportReststopInfos() {
        // 1) 전국 휴게소 정보 표준 데이터 import
        val reststopStandardDataList = reststopClient.importReststopStandardData()
        // 2) 노선별/방향별 편의 시설 현황(휴게소 key mapping)
        val reststopBaselineList = reststopClient.importReststopBaseline()
        // 3) 하이쉼 마루 (휴게소 key mapping)
        val reststopAdditionalList = reststopClient.importReststopAdditional()
        // 4) 주차장 정보
        val parkingLotList = reststopClient.importParkingLot()
        // 5) 주유소 정보
        val reststopOilPriceList = reststopClient.importReststopOilPrice()
        // 6) 충전소 정보
        val chargingStationList = reststopClient.importChargingStation()

        val standardDataMap = reststopStandardDataList.associateBy { it.name + "휴게소" }
        val baselineMap = reststopBaselineList.associateBy { it.name }
        val additionalMap = reststopAdditionalList.associateBy { it.name }
        val parkingLotMap = parkingLotList.associateBy { it.reststopName + "휴게소" }
        val oilPriceMap =
            reststopOilPriceList.associateBy { ReststopUtils.processGasStationName(it.serviceAreaName ?: "") }
        val chargingStationMap = chargingStationList.associateBy { it.facilityName + it.facilityType }

        standardDataMap.mapNotNull { (name, standardData) ->
            name.let {
                val processedName = if (name == "이천(하남)휴게소") {
                    "이천쌀(하남)휴게소"
                } else {
                    name
                }

                val baseline = baselineMap[processedName]
                val additional =
                    additionalMap[processedName] // 울주(함양)휴게소, 울주(울산)휴게소, 통도사(부산)휴게소, 김해금관가야휴게소, 매송(목포)휴게소, 매송(서울)휴게소
                val oilPrice = oilPriceMap[processedName]
                val parkingLot = parkingLotMap[name] // 이천(하남)으로 표기
                val chargingStation = chargingStationMap[processedName]

                if (baseline == null && additional == null) return@let null

                val reststop = Reststop(
                    name = processedName,
                    roadRouteNo = additional?.routeCd ?: baseline?.routeCode ?: "Unknown",
                    roadRouteName = standardData.roadRouteName ?: "Unknown",
                    roadRouteDirection = standardData.roadRouteDirection ?: "Unknown",
                    latitude = standardData.latitude?.toFloatOrNull() ?: 0f,
                    longitude = standardData.longitude?.toFloatOrNull() ?: 0f,
                    phoneNumber = standardData.phoneNumber ?: "Unknown",
                    referenceDate = standardData.referenceDate ?: "Unknown",
                    representativeFoodName = standardData.representativeFoodName ?: "Unknown",
                    reststopType = standardData.reststopType ?: "Unknown",
                    standardCode = additional?.standardCode ?: baseline?.standardCode ?: "Unknown",
                    serviceAreaCode = additional?.bsopAdtnlFcltCd ?: baseline?.serviceAreaCode ?: "Unknown",
                    address = additional?.svarAddr ?: baseline?.svarAddr ?: "Unknown",
                    smallCarSpace = parkingLot?.smallCarSpace,
                    largeCarSpace = parkingLot?.largeCarSpace,
                    disabledPersonSpace = parkingLot?.disabledPersonSpace,
                    totalSpace = parkingLot?.totalSpace,
                    gasolinePrice = oilPrice?.gasolinePrice,
                    dieselPrice = oilPrice?.dieselPrice,
                    lpgPrice = oilPrice?.lpgPrice,
                    hasElectricCharger = chargingStation?.electricChargingStation == "O",
                    hasHydrogenCharger = chargingStation?.hydrogenChargingStation == "O"
                )
                saveOrUpdateReststop(reststop)
            }
        }
    }

    private fun saveOrUpdateReststop(reststop: Reststop) {
        val existingReststop = reststopRepository.findByStandardCode(reststop.standardCode)

        if (existingReststop != null) {
            existingReststop.apply {
                name = reststop.name
                roadRouteNo = reststop.roadRouteNo
                roadRouteName = reststop.roadRouteName
                roadRouteDirection = reststop.roadRouteDirection
                latitude = reststop.latitude
                longitude = reststop.longitude
                phoneNumber = reststop.phoneNumber
                referenceDate = reststop.referenceDate
                representativeFoodName = reststop.representativeFoodName
                reststopType = reststop.reststopType
                serviceAreaCode = reststop.serviceAreaCode
                address = reststop.address
                smallCarSpace = reststop.smallCarSpace
                largeCarSpace = reststop.largeCarSpace
                disabledPersonSpace = reststop.disabledPersonSpace
                totalSpace = reststop.totalSpace
                gasolinePrice = reststop.gasolinePrice
                dieselPrice = reststop.dieselPrice
                lpgPrice = reststop.lpgPrice
                hasElectricCharger = reststop.hasElectricCharger
                hasHydrogenCharger = reststop.hasHydrogenCharger
            }
            reststopRepository.save(existingReststop)
        } else {
            reststopRepository.save(reststop)
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
        val start = 30
        val end = 50
        val randomValue = Random.nextInt(start, end + 1)
        return randomValue / 10.0f
    }

    @Transactional
    suspend fun updateRestaurantsHours() {
        val reststops = reststopRepository.findAll()
        reststops.forEach { reststop ->
            // TODO restaurantOpenTime 계산 로직 추가
            reststop.restaurantOpenTime = "07:00 ~ 23:30"
            reststopRepository.save(reststop)
        }
    }


}
