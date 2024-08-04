//package com.eightpotatoes.nexters.client.route
//
//import kotlinx.coroutines.reactive.awaitSingle
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Service
//import org.springframework.web.reactive.function.client.WebClient
//
//@Service
//class RouteInfoService(
//    @Value("\${api.key}") private val apiKey: String,
//    private val webClient: WebClient,
//) {
//    suspend fun callAndSaveAllRouteInfo(): List<RouteInfoOrigin> {
//
//        val responses = mutableListOf<ReststopFoodResponse>()
//        var page = 1
//
//        while (true) {
//            val response = callOpenAPIReststopFood(page)
//            if (response.list.isEmpty()) {
//                break
//            }
//            response.list.let { items ->
//                items.map { item -> item.toMenu() }
//                    .forEach { menu ->
//                        menuService.upsertMenu(menu)
//                    }
//            }
//            responses.add(response)
//            page++
//        }
//
//        return responses
//    }
//
//    private suspend fun callOpenAPIReststopFood(pageNo: Int): ReststopFoodResponse {
//        return webClient.get()
//            .uri { builder ->
//                builder.path("/openapi/restinfo/restBestfoodList")
//                    .queryParam("key", apiKey)
//                    .queryParam("type", "json")
//                    .queryParam("numOfRows", "99")
//                    .queryParam("pageNo", pageNo)
//                    .build()
//            }
//            .retrieve()
//            .bodyToMono(ReststopFoodResponse::class.java)
//            .awaitSingle()
//    }
//}