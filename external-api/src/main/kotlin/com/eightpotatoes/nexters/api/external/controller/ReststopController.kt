package com.eightpotatoes.nexters.api.external.controller

import com.eightpotatoes.nexters.api.external.model.ReststopsAtHighway
import com.eightpotatoes.nexters.api.external.service.ReststopExternalService
import com.eightpotatoes.nexters.core.util.LocationUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ReststopController(private val reststopExternalService: ReststopExternalService) {

    @GetMapping("/api/highways/reststops")
    fun getReststopsAtHighways(
        @RequestParam from: String,
        @RequestParam to: String,
        @RequestParam roadNames: List<String>
    ): Flux<ReststopsAtHighway> {
        val fromLat = from.toDouble()
        val toLat = to.toDouble()
        val direction = LocationUtils.determineDirection(fromLat, toLat)

        return reststopExternalService.getReststopsAtHighways(roadNames, direction)
    }
}
