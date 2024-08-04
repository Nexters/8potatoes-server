package com.eightpotatoes.nexters.api.external.service
import com.eightpotatoes.nexters.api.external.mapper.ReststopMapper
import com.eightpotatoes.nexters.api.external.model.ReststopDetailAtHighway
import com.eightpotatoes.nexters.api.external.model.ReststopsAtHighway
import com.eightpotatoes.nexters.core.repository.*
import org.springframework.stereotype.Service

import reactor.core.publisher.Flux

@Service
class ReststopExternalService(
    private val reststopRepository: ReststopRepository,
    private val menuRepository: MenuRepository,
) {
    fun getReststopsAtHighways(
        roadNameList: List<String>,
        direction: String
    ): Flux<ReststopsAtHighway> {
        val reststopDetailAtHighwayList = mutableListOf<ReststopDetailAtHighway>()

        val reststopList = reststopRepository.findByRoadRouteNameAndDirection(roadNameList, direction)
        reststopList.iterator().forEach {
            val reststopsAtHighway = ReststopMapper.toReststopsAtHighway(
                entity = it,
                isOperating = calculateIsOperating(it.reststopCode),
                foodMenusCount = calculateFoodMenusCount(it.reststopCode),
            )
            reststopDetailAtHighwayList.add(reststopsAtHighway)
        }
        return Flux.just(ReststopsAtHighway(reststopList.size, reststopDetailAtHighwayList))
    }

    private fun calculateFoodMenusCount(reststopId: String?): Int {
        return menuRepository.findByReststopId(reststopId).size
    }

    private fun calculateIsOperating(reststopId: String?): Boolean {
        // TODO: 휴게소 ID를 이용하여 해당 휴게소의 식당 운영 여부를 조회하는 로직 구현
        return true
    }
}