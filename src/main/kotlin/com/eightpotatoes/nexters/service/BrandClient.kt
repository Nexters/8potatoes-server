package com.eightpotatoes.nexters.service

import com.eightpotatoes.nexters.entity.Brand
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.scheduler.Schedulers
import java.util.Collections.emptyList

@Component
class BrandClient(val webClient: WebClient) {

    fun fullImportBrand(): List<Brand> {
        var pageNo = 0
        val brands = mutableListOf<Brand>()

        while (true) {
            val result = webClient.get()
                    .uri { builder ->
                        builder.path("/openapi/restinfo/restBrandList")
                                .queryParam("key", "test")
                                .queryParam("type", "json")
                                .queryParam("numOfRows", 100)
                                .queryParam("pageNo", pageNo)
                                .build()
                    }
                    .retrieve()
                    .bodyToMono(BrandOrigins::class.java)
                    .subscribeOn(Schedulers.boundedElastic())
                    .block()

            val responses: List<Brand> = result?.list?.map { item -> item.toBrand() } ?: emptyList()

            if (responses.isEmpty()) {
                break
            }

            brands.addAll(responses)
            pageNo++
        }

        return brands
    }
}
