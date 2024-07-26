//package com.eightpotatoes.nexters.service
//import com.eightpotatoes.nexters.entity.Reststop
//import com.eightpotatoes.nexters.model.ReststopDetails
//import com.eightpotatoes.nexters.repository.*
//import org.springframework.stereotype.Service
//
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//
//@Service
//class ReststopService(
//    private val reststopRepository: ReststopRepository,
//    private val directionRepository: DirectionRepository,
//    private val menuRepository: MenuRepository,
//    private val reststopBrandRepository: ReststopBrandRepository,
//    private val brandRepository: BrandRepository
//) {
//    fun getReststop(roadName: String, direction: String): Flux<Reststop> {
//        return directionRepository.findByName(direction)
//            .flatMapMany { direction ->
//                reststopRepository.findByDirectionId(direction.id)
//            }
//    }
//
//    fun getReststopDetails(reststopCode: String): Mono<ReststopDetails> {
//        return reststopRepository.findByCode(reststopCode)
//            .flatMap { reststop ->
//                Mono.zip(
//                    menuRepository.findByReststopId(reststop.id).collectList(),
//                    reststopBrandRepository.findByReststopId(reststop.id)
//                        .flatMap { reststopBrand ->
//                            brandRepository.findById(reststopBrand.brandId)
//                        }.collectList()
//                ).map { tuple ->
//                    ReststopDetails(
//                        name = reststop.name,
//                        address = reststop.address,
//                        isOperating = reststop.isOperating,
//                        operatingHours = reststop.operatingHours,
//                        fuelInfo = reststop.fuelInfo,
//                        naverRating = reststop.naverRating,
//                        foodMenus = tuple.t1,
//                        brands = tuple.t2
//                    )
//                }
//            }
//    }
//}