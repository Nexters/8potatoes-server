package com.eightpotatoes.nexters.client.reststop

import com.eightpotatoes.nexters.client.reststop.response.ReststopResponse
import com.eightpotatoes.nexters.core.entity.Reststop
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.scheduler.Schedulers
import java.net.URI
import java.util.*

@Component
class ReststopClient(private val apiDataClient: WebClient) {

    fun fullImportReststop(): List<Reststop> {
        var pageNo = 0
        val reststops = mutableListOf<Reststop>()

        while (true) {
            val uri = URI("http://api.data.go.kr/openapi/tn_pubr_public_rest_area_api?" +
                    "serviceKey=%2FfEPqoDVvMa7ESThPi2%2FrGzcfxIS4i4XQXIBIS2IFcPSas5TvhZP91KMEG7EI8I2PgohmqRy2qwBw%2B7LKFslrQ%3D%3D&pageNo=$pageNo&numOfRows=100&type=json")

            val result = apiDataClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(ReststopResponse::class.java)
                    .subscribeOn(Schedulers.boundedElastic())
                    .block()

            val responses: List<Reststop> = result?.response?.body?.items?.map { item -> item.toReststop() } ?: Collections.emptyList()

            if (responses.isEmpty()) {
                break
            }

            reststops.addAll(responses)
            pageNo++
        }

        return reststops
    }
}
