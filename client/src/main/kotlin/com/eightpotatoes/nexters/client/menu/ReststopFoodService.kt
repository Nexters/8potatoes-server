package com.eightpotatoes.nexters.client.menu

import com.eightpotatoes.nexters.client.menu.response.ReststopFoodResponse
import com.eightpotatoes.nexters.client.model.ReststopRecommendMenuData
import com.eightpotatoes.nexters.core.entity.Menu
import com.eightpotatoes.nexters.core.util.MenuUtils.getCategoryByName
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class ReststopFoodService(
    @Value("\${api.key}") private val apiKey: String,
    private val menuService: MenuService,
    private val webClient: WebClient,
) {
    suspend fun callAndUpsertAllFoodPages() = coroutineScope {
        val responses = mutableListOf<ReststopFoodResponse>()
        var page = 1
        val jobs = mutableListOf<Job>()

        while (true) {
            val response = callOpenAPIReststopFood(page)
            if (response.list.isEmpty()) {
                break
            }
            response.list.map { item -> item.toMenu() }.forEach { menu ->
                jobs += launch {
                    try {
                        menu.category = getCategoryByName(menu.name)
                        if (menu.isBestFood && menu.isPremium && menu.isRecommended) {
                            updateMenuDescription(menu)
                        }
                        menuService.upsertMenu(menu)
                    } catch (e: Exception) {
                        // Log error and handle it appropriately
                        println("Failed to upsert menu: ${e.message}")
                    }
                }
            }
            responses.add(response)
            page++
        }

        jobs.joinAll() // 모든 작업이 완료될 때까지 대기
        responses
    }

    private suspend fun callOpenAPIReststopFood(pageNo: Int): ReststopFoodResponse {
        return webClient.get()
            .uri { builder ->
                builder.path("/openapi/restinfo/restBestfoodList")
                    .queryParam("key", apiKey)
                    .queryParam("type", "json")
                    .queryParam("numOfRows", "99")
                    .queryParam("pageNo", pageNo)
                    .build()
            }
            .retrieve()
            .bodyToMono(ReststopFoodResponse::class.java)
            .awaitSingle()
    }

    // 휴게소 대표 추천 메뉴의 설명을 업데이트
    private fun updateMenuDescription(menu: Menu) {
        val matchingMenu = ReststopRecommendMenuData.menuList.find {
            it.standardCode == menu.reststopCode && it.foodName == menu.name
        }

        matchingMenu?.let {
            menu.description = it.description
        }
    }
}
