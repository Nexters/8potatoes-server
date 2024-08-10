package com.eightpotatoes.nexters.api.external.controller

import com.eightpotatoes.nexters.api.external.model.ReststopDetailResponse
import com.eightpotatoes.nexters.api.external.model.ReststopsAtHighway
import com.eightpotatoes.nexters.api.external.service.ReststopExternalService
import com.eightpotatoes.nexters.core.util.LocationUtils.determineDirection
import com.eightpotatoes.nexters.core.util.LocationUtils.parseCoordinates
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

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
        @Parameter(
            description = "Starting coordinate in 'lat,lon' format",
            required = true,
            example = "35.4888,129.091"
        )
        @RequestParam from: String,
        @Parameter(description = "Ending coordinate in 'lat,lon' format", required = true, example = "36.1234,128.4567")
        @RequestParam to: String,
        @Parameter(description = "List of road names", required = true, example = "서해안선,영동선")
        @RequestParam roadNames: String
    ): Flux<ReststopsAtHighway> {
        val fromCoordinates = parseCoordinates(from)
        val toCoordinates = parseCoordinates(to)
        val direction = determineDirection(fromCoordinates, toCoordinates)
        val roadNameList = roadNames.split(",")
        return reststopExternalService.getReststopsAtHighways(roadNameList, direction)
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
    ): Flux<ReststopDetailResponse> {
        return reststopExternalService.getReststopInfo(reststopCode)
    }
}
