package com.eightpotatoes.nexters.client.reststop

import com.eightpotatoes.nexters.client.reststop.response.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.scheduler.Schedulers
import java.net.URI

@Component
class ReststopClient(
    @Value("\${api.key}") private val apiKey: String,
    private val apiDataClient: WebClient
) {

    /**
     * Import reststop standard data(전국 휴게소 정보 표준 데이터)
     *  - https://www.data.go.kr/data/15025446/standard.do
     *
     * @return List<ReststopOrigin> 전국 휴게소 정보 표준 데이터
     */
    fun importReststopStandardData(): List<ReststopOrigin> {
        val reststops = mutableListOf<ReststopOrigin>()
        var pageNo = 1

        while (true) {
            val uri = buildUri(pageNo)
            val result = fetchReststopData(uri)

            val responses = result?.response?.body?.items ?: emptyList()
            if (responses.isEmpty()) {
                break
            }

            reststops.addAll(responses)
            pageNo++
        }

        return reststops
    }

    private fun buildUri(pageNo: Int): URI {
        val serviceKey =
            "%2FfEPqoDVvMa7ESThPi2%2FrGzcfxIS4i4XQXIBIS2IFcPSas5TvhZP91KMEG7EI8I2PgohmqRy2qwBw%2B7LKFslrQ%3D%3D"
        val baseUrl = "http://api.data.go.kr/openapi/tn_pubr_public_rest_area_api"
        val params = "serviceKey=$serviceKey&pageNo=$pageNo&numOfRows=100&type=json"
        return URI("$baseUrl?$params")
    }

    private fun fetchReststopData(uri: URI): ReststopOriginResponse? {
        return try {
            apiDataClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ReststopOriginResponse::class.java)
                .subscribeOn(Schedulers.boundedElastic())
                .block()
        } catch (ex: Exception) {
            println("Error fetching data: ${ex.message}")
            null
        }
    }

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
                        .queryParam("numOfRows", "99")
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

    /**
     * Import reststop baseline data(휴게소 기준 정보 for Key mapping)
     * - https://www.data.go.kr/data/15076641/openapi.do
     *
     * @return List<ReststopBaselineOrigin> 휴게소 기준 정보
     */
    fun importReststopBaseline(): List<ReststopBaselineOrigin> {
        return importData<ReststopBaselineOrigin, ReststopBaselineOriginBody>("openapi/business/conveniServiceArea") { it?.list }
    }

    /**
     * Import reststop additional data(휴게소 추가 기준 정보 for Key mapping)
     * - https://www.data.go.kr/data/15062047/openapi.do
     *
     * @return List<ReststopAdditionalOrigin> 휴게소 추가 기준 정보
     */
    fun importReststopAdditional(): List<ReststopAdditionalOrigin> {
        val result = apiDataClient.get()
            .uri { builder ->
                builder.path("openapi/restinfo/hiwaySvarInfoList")
                    .queryParam("key", apiKey)
                    .queryParam("type", "json")
                    .queryParam("svarGsstClssCd", 0) // 0 : 휴게소
                    .build()
            }
            .retrieve()
            .bodyToMono(ReststopAdditionalOriginBody::class.java)
            .subscribeOn(Schedulers.boundedElastic())
            .block()

        return result?.list ?: emptyList()
    }

    /**
     * Import reststop oil price data(주유소 정보)
     * - https://www.data.go.kr/data/15076636/openapi.do?recommendDataYn=Y
     *
     * @return List<GasStationOrigin> 주유소 정보
     */
    fun importReststopOilPrice(): List<GasStationOrigin> {
        return importData<GasStationOrigin, GasStationOriginBody>("/openapi/business/curStateStation") { it?.list }
    }

    /**
     * Import charging station data(충전소 정보)
     * - https://www.data.go.kr/tcs/dss/selectFileDataDetailView.do?publicDataPk=15085543
     *
     * @return List<ChargingStationOrigin> 충전소 정보
     */
    fun importChargingStation(): List<ChargingStationOrigin> {
        return fetchPagedData(
            { pageNo -> buildChargingStationUri(pageNo) },
            { result: ChargingStationOriginBody? -> result?.data ?: emptyList() }
        )
    }

    /**
     * Import parking lot data(주차장 정보)
     * - https://www.data.go.kr/tcs/dss/selectFileDataDetailView.do?publicDataPk=15043716
     *
     * @return List<ParkingLotOrigin> 주차장 정보
     */
    fun importParkingLot(): List<ParkingLotOrigin> {
        return fetchPagedData(
            { pageNo -> buildParkingLotUri(pageNo) },
            { result: ParkingLotOriginBody? -> result?.data ?: emptyList() }
        )
    }

    private fun buildChargingStationUri(pageNo: Int): URI {
        return URI(
            "https://api.odcloud.kr/api/15085543/v1/uddi:2ebdbe65-86c4-4481-b992-e14482ab1d80?page=$pageNo&perPage=100" +
                    "&serviceKey=p6zNXOJrrBY4cuX7OYtdDMtmR8hiGeUaBLf0z6BXnm%2FqniV8wB0SuPwBgqKDTKV%2F24EW7xiRY3DCS21Ess%2F42Q%3D%3D"
        )
    }

    private fun buildParkingLotUri(pageNo: Int): URI {
        return URI(
            "https://api.odcloud.kr/api/15043716/v1/uddi:59c55434-aa9c-47d4-93a3-b1a140826e5f?page=$pageNo&perPage=100" +
                    "&serviceKey=p6zNXOJrrBY4cuX7OYtdDMtmR8hiGeUaBLf0z6BXnm%2FqniV8wB0SuPwBgqKDTKV%2F24EW7xiRY3DCS21Ess%2F42Q%3D%3D"
        )
    }

    private inline fun <reified T, reified R> fetchPagedData(
        uriBuilder: (Int) -> URI,
        responseExtractor: (T?) -> List<R>
    ): List<R> {
        var pageNo = 1
        val dataList = mutableListOf<R>()

        while (true) {
            val uri = uriBuilder(pageNo)
            val result: T? = try {
                apiDataClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(T::class.java)
                    .subscribeOn(Schedulers.boundedElastic())
                    .block()
            } catch (ex: Exception) {
                println("Error fetching data: ${ex.message}")
                null
            }

            val responses: List<R> = responseExtractor(result)

            if (responses.isEmpty()) {
                break
            }

            dataList.addAll(responses)
            pageNo++
        }

        return dataList
    }
}
