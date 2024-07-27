package com.eightpotatoes.nexters.repository

import com.eightpotatoes.nexters.entity.Menu
import io.r2dbc.spi.Parameters
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class CustomMenuRepositoryImpl(
    private val databaseClient: DatabaseClient
) : CustomMenuRepository {
    override suspend fun upsertMenu(menu: Menu): Menu {
        val query = """
            INSERT INTO menu (name, food_seq, price, description, is_recommended, is_premium, is_best_food, reststop_id, created_at, updated_at)
            VALUES (:name, :foodSeq, :price, :description, :isRecommended, :isPremium, :isBestFood, :reststopId, :createdAt, :updatedAt)
            ON DUPLICATE KEY UPDATE
                name = VALUES(name),
                price = VALUES(price),
                description = VALUES(description),
                is_recommended = VALUES(is_recommended),
                is_premium = VALUES(is_premium),
                is_best_food = VALUES(is_best_food),
                reststop_id = VALUES(reststop_id),
                updated_at = VALUES(updated_at)
        """.trimIndent()

        databaseClient.sql(query)
            .bind("name", menu.name)
            .bind("foodSeq", menu.foodSeq)
            .bind("price", menu.price)
            .bind("description", menu.description ?: Parameters.`in`(String::class.java))
            .bind("isRecommended", menu.isRecommended)
            .bind("isPremium", menu.isPremium)
            .bind("isBestFood", menu.isBestFood)
            .bind("reststopId", menu.reststopId)
            .bind("createdAt", menu.createdAt ?: LocalDateTime.now())
            .bind("updatedAt", LocalDateTime.now())
            .fetch()
            .rowsUpdated()
            .awaitSingle()

        return menu
    }
}