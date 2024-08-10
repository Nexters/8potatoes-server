package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Menu
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import java.time.LocalDateTime

open class MenuRepositoryCustomImpl : MenuRepositoryCustom {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun upsert(menu: Menu) {
        if (menu.createdAt == null) {
            menu.createdAt = LocalDateTime.now()
        }
        menu.modifiedAt = LocalDateTime.now()

        val sql = """
            INSERT INTO menu (description, food_seq, is_best_food, is_premium, is_recommended, name, price, category, reststop_code, created_at, modified_at) 
            VALUES (:description, :foodSeq, :isBestFood, :isPremium, :isRecommended, :name, :price, :category, :reststopCode, :createdAt, :modifiedAt) 
            ON DUPLICATE KEY UPDATE 
            description = VALUES(description), 
            is_best_food = VALUES(is_best_food), 
            is_premium = VALUES(is_premium), 
            is_recommended = VALUES(is_recommended), 
            price = VALUES(price), 
            category = VALUES(category),
            food_seq = VALUES(food_seq),
            modified_at = VALUES(modified_at)
        """

        val query = entityManager.createNativeQuery(sql).apply {
            setParameter("name", menu.name)
            setParameter("reststopCode", menu.reststopCode)
            setParameter("description", menu.description)
            setParameter("isBestFood", menu.isBestFood)
            setParameter("isPremium", menu.isPremium)
            setParameter("isRecommended", menu.isRecommended)
            setParameter("price", menu.price)
            setParameter("foodSeq", menu.foodSeq)
            setParameter("category", menu.category.name)
            setParameter("createdAt", menu.createdAt)
            setParameter("modifiedAt", menu.modifiedAt)
        }
        query.executeUpdate()
    }
}
