package com.eightpotatoes.nexters.api.external.mapper

import com.eightpotatoes.nexters.api.external.model.MenuData
import com.eightpotatoes.nexters.api.external.model.RepresentativeMenuData
import com.eightpotatoes.nexters.api.external.model.ReststopMenuData
import com.eightpotatoes.nexters.core.entity.Menu

object MenuMapper {
    // 변환 함수
    fun buildReststopMenuData(
        menus: List<Menu>,
        baseUrl: String,
    ): ReststopMenuData {
        // 대표 메뉴 데이터 (bestfood, premium, recommend 모두 true인 경우)
        val representativeMenuData = menus
            .filter { it.isBestFood && it.isPremium && it.isRecommended }
            .take(2)
            .map {
                RepresentativeMenuData(
                    representativeMenuName = it.name,
                    representativeMenuPrice = it.price,
                    representativeMenuImageUrl = "$baseUrl/menu/${it.name}.svg",
                    representativeMenuDescription = it.description ?: ""
                )
            }

        // 태그 개수를 계산
        fun countTags(menu: Menu): Int {
            var count = 0
            if (menu.isBestFood) count++
            if (menu.isPremium) count++
            return count
        }

        // 추천 메뉴 데이터 (bestfood 또는 premium 태그가 하나 이상인 경우)
        val recommendedMenuData = menus
            .filter { it.isBestFood || it.isPremium }
            .sortedWith(compareByDescending<Menu> { countTags(it) }
                .thenByDescending { it.isBestFood }
                .thenByDescending { it.isPremium })
            .map {
                MenuData(
                    menuName = it.name,
                    menuPrice = it.price,
                    isSignatureMenu = it.isPremium,
                    isPopularMenu = it.isBestFood,
                    menuCategory = it.category.value
                )
            }

        // 일반 메뉴 데이터
        val normalMenuData = menus
            .sortedWith(compareByDescending<Menu> { countTags(it) }
                .thenByDescending { it.isBestFood }
                .thenByDescending { it.isPremium }
                .thenBy { it.name })
            .map {
                MenuData(
                    menuName = it.name,
                    menuPrice = it.price,
                    isSignatureMenu = it.isPremium,
                    isPopularMenu = it.isBestFood,
                    menuCategory = it.category.value
                )
            }

        // 전체 메뉴 개수
        val totalMenuCount = menus.size

        return ReststopMenuData(
            representativeMenuData = representativeMenuData,
            totalMenuCount = totalMenuCount,
            recommendedMenuData = recommendedMenuData,
            normalMenuData = normalMenuData
        )
    }

}