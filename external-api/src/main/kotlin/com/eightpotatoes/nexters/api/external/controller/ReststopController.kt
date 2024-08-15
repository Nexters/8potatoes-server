package com.eightpotatoes.nexters.api.external.controller

import com.eightpotatoes.nexters.api.external.model.ReststopDetailResponse
import com.eightpotatoes.nexters.api.external.model.ReststopsAtHighway
import com.eightpotatoes.nexters.api.external.service.ReststopExternalService
import com.eightpotatoes.nexters.core.util.LocationUtils.calculateMidPoint
import com.eightpotatoes.nexters.core.util.LocationUtils.calculateMiddleZone
import com.eightpotatoes.nexters.core.util.LocationUtils.determineDirection
import com.eightpotatoes.nexters.core.util.LocationUtils.parseLocation
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@Tag(name = "휴게소 데이터 관련 API", description = "휴게소 관련 데이터를 조회할 수 있습니다.")
class ReststopController(private val reststopExternalService: ReststopExternalService) {

    @Operation(
        summary = "[GET] 경로 내 휴게소 조회 API",
        description = "출발지와 도착지 사이에 도로 노선을 따라 존재하는 휴게소 목록을 조회합니다.",
        responses = [
            ApiResponse(
                description = "Successfully retrieved list",
                responseCode = "200",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(description = "Bad request", responseCode = "400", content = [Content()]),
            ApiResponse(description = "Internal server error", responseCode = "500", content = [Content()])
        ]
    )
    @GetMapping("/api/highways/reststops")
    fun getReststopsAtHighways(
        @Parameter(description = "출발지 좌표 'lat,lon'", required = true, example = "37.3171,127.0866")
        @RequestParam from: String,
        @Parameter(description = "도착지 좌표 'lat,lon'", required = true, example = "34.9564,127.58748")
        @RequestParam to: String,
        @Parameter(description = "노선 이름 List(구분자',')", required = true, example = "경부선,호남선,순천완주선")
        @RequestParam roadNames: String
    ): Flux<ReststopsAtHighway> {
        val fromLocation = parseLocation(from)
        val toLocation = parseLocation(to)
        return reststopExternalService.getReststopsAtHighways(
            fromLocation = fromLocation,
            roadNameList = roadNames.split(","),
            direction = determineDirection(fromLocation, toLocation),
            midPoint = calculateMidPoint(fromLocation, toLocation),
            middleZone = calculateMiddleZone(fromLocation, toLocation),
        )
    }

    @Operation(
        summary = "[GET] 휴게소 상세 정보(메뉴, 기본 정보) 조회 API",
        description = "특정 휴게소의 상세 정보를 조회합니다.",
        responses = [
            ApiResponse(
                description = "Successfully retrieved information",
                responseCode = "200",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(description = "Bad request", responseCode = "400", content = [Content()]),
            ApiResponse(description = "Internal server error", responseCode = "500", content = [Content()])
        ]
    )
    @GetMapping("/api/reststop/info")
    fun getReststopInfo(
        @Parameter(description = "Reststop Code", required = true, example = "000013")
        @RequestParam reststopCode: String
    ): Mono<ReststopDetailResponse> {
        return reststopExternalService.getReststopInfo(reststopCode)
    }
}
