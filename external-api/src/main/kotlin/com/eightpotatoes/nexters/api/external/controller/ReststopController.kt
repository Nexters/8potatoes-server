package com.eightpotatoes.nexters.api.external.controller

import com.eightpotatoes.nexters.api.external.model.HighwayRequest
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
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody

@RestController
@Tag(name = "휴게소 데이터 관련 API", description = "휴게소 관련 데이터를 조회할 수 있습니다.")
class ReststopController(private val reststopExternalService: ReststopExternalService) {

    @Operation(
        summary = "[POST] 경로 내 휴게소 조회 API",
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
    @PostMapping("/api/highways/reststops")
    fun getReststopsAtHighways(
        @Parameter(description = "출발지 좌표 'lat,lon'", required = true, example = "37.5431112,126.9821125")
        @RequestParam from: String,
        @Parameter(description = "도착지 좌표 'lat,lon'", required = true, example = "35.5597367,127.8157298")
        @RequestParam to: String,
        @SwaggerRequestBody(
            description = "고속도로 구간별 데이터",
            required = true,
            content = [Content(
                examples = [ExampleObject(
                    name = "HighwayRequestExample",
                    value = HIGHWAY_REQUEST_EXAMPLE
                )]
            )]
        )
        @RequestBody highwayRequest: HighwayRequest,
    ): Flux<ReststopsAtHighway> {
        val fromLocation = parseLocation(from)
        val toLocation = parseLocation(to)
        return reststopExternalService.getReststopsAtHighways(
            fromLocation = fromLocation,
            highwayRequest = highwayRequest,
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

    companion object {
        const val HIGHWAY_REQUEST_EXAMPLE = """
{
  "highways": {
    "경부 고속도로": [
      [
        [
          127.08543779752235,
          37.21774152026536
        ],
        [
          127.09485641007603,
          37.21774152026536
        ],
        [
          127.08543779752235,
          37.21632224543946
        ],
        [
          127.09485641007603,
          37.21632224543946
        ]
      ],
      [
        [
          127.0871670924697,
          37.21640850022002
        ],
        [
          127.09624519973312,
          37.21640850022002
        ],
        [
          127.0871670924697,
          37.15722910006451
        ],
        [
          127.09624519973312,
          37.15722910006451
        ]
      ],
      [
        [
          127.08338161645169,
          37.15722910006451
        ],
        [
          127.12021299657435,
          37.15722910006451
        ],
        [
          127.08338161645169,
          37.097153303863195
        ],
        [
          127.12021299657435,
          37.097153303863195
        ]
      ],
      [
        [
          127.12021299657435,
          37.097153303863195
        ],
        [
          127.18206690562984,
          37.097153303863195
        ],
        [
          127.12021299657435,
          36.95515413954567
        ],
        [
          127.18206690562984,
          36.95515413954567
        ]
      ],
      [
        [
          127.18206690562984,
          36.95515413954567
        ],
        [
          127.18941657136901,
          36.95515413954567
        ],
        [
          127.18206690562984,
          36.91536996091402
        ],
        [
          127.18941657136901,
          36.91536996091402
        ]
      ],
      [
        [
          127.16500035086526,
          36.91536996091402
        ],
        [
          127.42900810258438,
          36.91536996091402
        ],
        [
          127.16500035086526,
          36.56970289866972
        ],
        [
          127.42900810258438,
          36.56970289866972
        ]
      ],
      [
        [
          129.00405029959754,
          35.9232721259512
        ],
        [
          129.1969267252899,
          35.9232721259512
        ],
        [
          129.00405029959754,
          35.59629209533116
        ],
        [
          129.1969267252899,
          35.59629209533116
        ]
      ],
      [
        [
          129.0439018077258,
          35.59629209533116
        ],
        [
          129.13935314471962,
          35.59629209533116
        ],
        [
          129.0439018077258,
          35.344940811027634
        ],
        [
          129.13935314471962,
          35.344940811027634
        ]
      ],
      [
        [
          129.03975235183123,
          35.344940811027634
        ],
        [
          129.0439018077258,
          35.344940811027634
        ],
        [
          129.03975235183123,
          35.33833869788435
        ],
        [
          129.0439018077258,
          35.33833869788435
        ]
      ],
      [
        [
          129.03964406257919,
          35.33833869788435
        ],
        [
          129.10871479516058,
          35.33833869788435
        ],
        [
          129.03964406257919,
          35.25344378370259
        ],
        [
          129.10871479516058,
          35.25344378370259
        ]
      ],
      [
        [
          129.0967886603519,
          35.25344378370259
        ],
        [
          129.09981890169848,
          35.25344378370259
        ],
        [
          129.0967886603519,
          35.24898872371351
        ],
        [
          129.09981890169848,
          35.24898872371351
        ]
      ]
    ],
    "당진영덕 고속도로": [
      [
        [
          127.42799995760286,
          36.56970289866972
        ],
        [
          127.65675981308195,
          36.56970289866972
        ],
        [
          127.42799995760286,
          36.47322637666793
        ],
        [
          127.65675981308195,
          36.47322637666793
        ]
      ],
      [
        [
          127.65675981308195,
          36.47322637666793
        ],
        [
          127.76940304158524,
          36.47322637666793
        ],
        [
          127.65675981308195,
          36.454399922268784
        ],
        [
          127.76940304158524,
          36.454399922268784
        ]
      ],
      [
        [
          127.76940304158524,
          36.45565115826513
        ],
        [
          128.0696164660673,
          36.45565115826513
        ],
        [
          127.76940304158524,
          36.4075913757038
        ],
        [
          128.0696164660673,
          36.4075913757038
        ]
      ],
      [
        [
          128.0696164660673,
          36.4075913757038
        ],
        [
          128.2290589820288,
          36.4075913757038
        ],
        [
          128.0696164660673,
          36.366696191104445
        ],
        [
          128.2290589820288,
          36.366696191104445
        ]
      ]
    ],
    "상주영천 고속도로": [
      [
        [
          128.2290589820288,
          36.37514040610051
        ],
        [
          128.26926350407288,
          36.37514040610051
        ],
        [
          128.2290589820288,
          36.36613804288008
        ],
        [
          128.26926350407288,
          36.36613804288008
        ]
      ],
      [
        [
          128.26926350407288,
          36.37513485695989
        ],
        [
          128.49417246283525,
          36.37513485695989
        ],
        [
          128.26926350407288,
          36.23631594393996
        ],
        [
          128.49417246283525,
          36.23631594393996
        ]
      ],
      [
        [
          128.49417246283525,
          36.23631594393996
        ],
        [
          128.90958920169857,
          36.23631594393996
        ],
        [
          128.49417246283525,
          36.01723190040396
        ],
        [
          128.90958920169857,
          36.01723190040396
        ]
      ],
      [
        [
          128.90958920169857,
          36.01723190040396
        ],
        [
          129.01976480604,
          36.01723190040396
        ],
        [
          128.90958920169857,
          35.9232721259512
        ],
        [
          129.01976480604,
          35.9232721259512
        ]
      ]
    ]
  }
}
        """
    }
}
