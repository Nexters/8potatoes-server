package com.eightpotatoes.nexters.client.reststop

import com.eightpotatoes.nexters.client.reststop.response.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.scheduler.Schedulers
import java.net.URI
import java.util.*

@Component
class ReststopClient(
    @Value("\${api.key}") private val apiKey: String,
    private val apiDataClient: WebClient
) {

    private inline fun <reified T, reified R> importData(
        path: String,
        noinline extractList: (R?) -> List<T>?
    ): List<T> {
        var pageNo = 1
        val dataList = mutableListOf<T>()

        while (true) {
            val result = apiDataClient.get()
                .uri { builder ->
                    builder.path(path)
                        .queryParam("key", apiKey)
                        .queryParam("type", "json")
                        .queryParam("numOfRows", "20")
                        .queryParam("pageNo", pageNo)
                        .build()
                }
                .retrieve()
                .bodyToMono(R::class.java)
                .subscribeOn(Schedulers.boundedElastic())
                .block()

            val responses: List<T> = extractList(result) ?: emptyList()

            if (responses.isEmpty()) {
                break
            }

            dataList.addAll(responses)
            pageNo++
        }

        return dataList
    }

    fun importReststopBaseline(): List<ReststopBaselineOrigin> {
        return importData<ReststopBaselineOrigin, ReststopBaselineOriginBody>("openapi/locationinfo/locationinfoRest") { it?.list }
    }

    fun importReststopParkingLot(): List<ReststopParkingLotOrigin> {
        return importData<ReststopParkingLotOrigin, ReststopParkingLotOriginBody>("openapi/restinfo/hiwaySvarInfoList") { it?.list }
    }

    fun importReststopOilPrice(): List<GasStationOrigin> {
        return importData<GasStationOrigin, GasStationOriginBody>("/openapi/business/curStateStation") { it?.list }
    }

    fun importChargingStation(): List<ChargingStationOrigin> {
        var pageNo = 1
        val reststops = mutableListOf<ChargingStationOrigin>()

        while (true) {
            val uri = URI(
                "https://api.odcloud.kr/api/15085543/v1/uddi:2ebdbe65-86c4-4481-b992-e14482ab1d80?page=$pageNo&perPage=300" +
                        "&serviceKey=p6zNXOJrrBY4cuX7OYtdDMtmR8hiGeUaBLf0z6BXnm%2FqniV8wB0SuPwBgqKDTKV%2F24EW7xiRY3DCS21Ess%2F42Q%3D%3D"
            )

            val result = apiDataClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ChargingStationOriginBody::class.java)
                .subscribeOn(Schedulers.boundedElastic())
                .block()

            val responses: List<ChargingStationOrigin> = result?.data ?: Collections.emptyList()

            if (responses.isEmpty()) {
                break
            }

            reststops.addAll(responses)
            pageNo++
        }

        return reststops
    }
}
