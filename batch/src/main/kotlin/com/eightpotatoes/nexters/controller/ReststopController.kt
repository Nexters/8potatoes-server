//package com.eightpotatoes.nexters.controller
//
//import com.eightpotatoes.nexters.entity.Reststop
//import com.eightpotatoes.nexters.model.ReststopDetails
//import com.eightpotatoes.nexters.service.ReststopService
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RestController
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//
//@RestController
//class ReststopController(private val reststopService: ReststopService) {
//
//    @GetMapping("/api/highways/{roadName}/direction/{direction}/reststops")
//    fun getReststops(
//        @PathVariable roadName: String,
//        @PathVariable direction: String
//    ): Flux<Reststop> {
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