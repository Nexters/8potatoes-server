package com.eightpotatoes.nexters.client.brand

import com.eightpotatoes.nexters.client.brand.response.BrandOrigins
import com.eightpotatoes.nexters.core.entity.Brand
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.scheduler.Schedulers
import java.util.Collections.emptyList

@Component
class BrandClient(val webClient: WebClient) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${cloud.aws.s3.endpoint}")
    private val baseUrl: String? = null

    fun fullImportBrand(): List<Brand> {
        var pageNo = 1
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

            val responses: List<Brand> = result?.list?.map { item ->
                val filePath = "brand/${item.brdName}.svg"
                val thumbnailUrl = "$baseUrl/$bucket/$filePath"
                item.toBrand(thumbnailUrl)
            } ?: emptyList()

            if (responses.isEmpty()) {
                break
            }

            brands.addAll(responses)
            pageNo++
        }

        return brands
    }
}
