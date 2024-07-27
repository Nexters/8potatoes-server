package com.eightpotatoes.nexters.api.external.model

data class ReststopsAtHighway(
    val totalReststopCount: Int, // 총 {N}개의 휴게소
    val reststops: List<ReststopDetails>, // 휴게소 리스트
)
