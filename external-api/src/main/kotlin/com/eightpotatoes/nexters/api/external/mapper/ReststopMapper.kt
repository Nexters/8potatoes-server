package com.eightpotatoes.nexters.api.external.mapper

import com.eightpotatoes.nexters.api.external.model.ReststopDetailAtHighway
import com.eightpotatoes.nexters.core.entity.Reststop

object ReststopMapper {
    fun toReststopsAtHighway(
        entity: Reststop,
        isOperating : Boolean,
        foodMenusCount : Int
    ): ReststopDetailAtHighway {
        return ReststopDetailAtHighway(
            name = entity.name,
            isOperating = isOperating,
            gasolinePrice = entity.gasolinePrice,
            dieselPrice = entity.dieselPrice,
            lpgPrice = entity.lpgPrice,
            naverRating = entity.naverRating,
            foodMenusCount =foodMenusCount
        )
    }
}