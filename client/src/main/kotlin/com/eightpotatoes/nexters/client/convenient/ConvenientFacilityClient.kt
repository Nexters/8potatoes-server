package com.eightpotatoes.nexters.client.convenient

import com.eightpotatoes.nexters.client.convenient.response.ConvenientFacilityOrigin
import com.eightpotatoes.nexters.client.convenient.response.ConvenientFacilityOrigins
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.scheduler.Schedulers
import java.util.Collections.emptyList

@Component
class ConvenientFacilityClient(val webClient: WebClient) {

    fun fullImportConvenientFacilities(): List<ConvenientFacilityOrigin> {
        var pageNo = 0
        val convenientFacilities = mutableListOf<ConvenientFacilityOrigin>()

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

            val responses: List<ConvenientFacilityOrigin> = result?.list ?: emptyList()

            if (responses.isEmpty()) {
                break
            }

            convenientFacilities.addAll(responses)
            pageNo++
        }

        return convenientFacilities
    }
}
