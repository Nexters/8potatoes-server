package com.eightpotatoes.nexters.core.repository

import com.eightpotatoes.nexters.core.entity.Menu
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional

open class MenuRepositoryCustomImpl : MenuRepositoryCustom {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun upsert(menu: Menu) {
        val sql = """
            INSERT INTO menu (description, food_seq, is_best_food, is_premium, is_recommended, name, price, reststop_id, created_at, modified_at) 
            VALUES (:description, :foodSeq, :isBestFood, :isPremium, :isRecommended, :name, :price, :reststopId, :createdAt, :modifiedAt) 
            ON DUPLICATE KEY UPDATE 
            description = VALUES(description), 
            is_best_food = VALUES(is_best_food), 
            is_premium = VALUES(is_premium), 
            is_recommended = VALUES(is_recommended), 
            modified_at = VALUES(modified_at), 
            price = VALUES(price), 
            food_seq = VALUES(food_seq)
        """

        val query = entityManager.createNativeQuery(sql).apply {
            setParameter("createdAt", menu.createdAt)
            setParameter("description", menu.description)
            setParameter("foodSeq", menu.foodSeq)
            setParameter("isBestFood", menu.isBestFood)
            setParameter("isPremium", menu.isPremium)
            setParameter("isRecommended", menu.isRecommended)
            setParameter("modifiedAt", menu.modifiedAt)
            setParameter("name", menu.name)
            setParameter("price", menu.price)
            setParameter("reststopId", menu.reststopId)
        }
        query.executeUpdate()
    }
}
