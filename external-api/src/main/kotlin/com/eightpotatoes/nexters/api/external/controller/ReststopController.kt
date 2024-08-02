//package com.eightpotatoes.nexters.api.external.controller
//
//import com.eightpotatoes.nexters.api.external.model.ReststopsAtHighway
//import com.eightpotatoes.nexters.api.external.service.ReststopService
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//
//@RestController
//class ReststopController(private val reststopService: ReststopService) {
//
//    @GetMapping("/api/highways/reststops")
//    fun getReststops(
//        @RequestParam from: String,
//        @RequestParam to: String,
//        @RequestParam roadName: List<String>,
//    ): Flux<ReststopsAtHighway> {
//        return reststopService.getReststop(roadName, direction)
//    }
//
//    @GetMapping("/api/reststop/{reststopCode}")
//    fun getReststopDetails(
//        @PathVariable reststopCode: String
//    ): Mono<ReststopDetails> {
//        return reststopService.getReststopDetails(reststopCode)
//    }
//}