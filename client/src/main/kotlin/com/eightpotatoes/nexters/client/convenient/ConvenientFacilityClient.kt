package com.eightpotatoes.nexters.client.convenient

import com.eightpotatoes.nexters.client.convenient.response.ConvenientFacilityOrigins
import com.eightpotatoes.nexters.core.entity.ConvenientFacility
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.scheduler.Schedulers
import java.util.Collections.emptyList

@Component
class ConvenientFacilityClient(val webClient: WebClient) {

    fun fullImportConvenientFacilities(): List<ConvenientFacility> {
        var pageNo = 0
        val convenientFacilities = mutableListOf<ConvenientFacility>()

        while (true) {
            val result = webClient.get()
                    .uri { builder ->
                        builder.path("/openapi/restinfo/restConvList")
                                .queryParam("key", "test")
                                .queryParam("type", "json")
                                .queryParam("numOfRows", 200)
                                .queryParam("pageNo", pageNo)
                                .build()
                    }
                    .retrieve()
                    .bodyToMono(ConvenientFacilityOrigins::class.java)
                    .subscribeOn(Schedulers.boundedElastic())
                    .block()

            val responses: List<ConvenientFacility> = result?.list?.map { item -> item.toConvenientFacility() } ?: emptyList()

            if (responses.isEmpty()) {
                break
            }

            convenientFacilities.addAll(responses)
            pageNo++
        }

        return convenientFacilities
    }
}
