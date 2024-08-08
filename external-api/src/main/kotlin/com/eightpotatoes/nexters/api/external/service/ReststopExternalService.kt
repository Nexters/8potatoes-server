package com.eightpotatoes.nexters.api.external.service

import com.eightpotatoes.nexters.api.external.mapper.ReststopMapper
import com.eightpotatoes.nexters.api.external.model.BrandData
import com.eightpotatoes.nexters.api.external.model.ReststopDetailAtHighway
import com.eightpotatoes.nexters.api.external.model.ReststopDetailResponse
import com.eightpotatoes.nexters.api.external.model.ReststopsAtHighway
import com.eightpotatoes.nexters.core.repository.*
import com.eightpotatoes.nexters.core.util.ReststopUtils.isRestaurantOpen
import org.springframework.stereotype.Service

import reactor.core.publisher.Flux

@Service
class ReststopExternalService(
    private val reststopRepository: ReststopRepository,
    private val menuRepository: MenuRepository,
    private val brandRepository: BrandRepository,
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
                isOperating = isRestaurantOpen(it.restaurantOpenTime ?: "00:00 ~ 23:59"),
                foodMenusCount = calculateFoodMenusCount(it.standardCode),
            )
            reststopDetailAtHighwayList.add(reststopsAtHighway)
        }
        return Flux.just(ReststopsAtHighway(reststopList.size, reststopDetailAtHighwayList))
    }

    private fun calculateFoodMenusCount(reststopCode: String?): Int {
        return menuRepository.findByReststopCode(reststopCode).size
    }

    fun getReststopInfo(reststopCode: String): Flux<ReststopDetailResponse> {
        // 휴게소 정보 조회
        val reststop = reststopRepository.findByStandardCode(reststopCode)
            ?: throw IllegalArgumentException("Reststop not found")
        // 휴게소 메뉴 조회
        val menus = menuRepository.findByReststopCode(reststopCode)
        // 입점 브랜드 조회
        val brands = brandRepository.findByReststopCode(reststopCode).map {
            BrandData(
                brandName = it.name,
                brandLogoUrl = it.thumbnailUrl
            )
        }
        return Flux.just(ReststopMapper.toReststopDetailResponse(reststop, menus, brands))
    }
}