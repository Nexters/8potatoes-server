package com.eightpotatoes.nexters.entity

import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
open class BaseDateTimeEntity {
    var createdAt: LocalDateTime? = null
    var modifiedAt: LocalDateTime? = null

    @PrePersist
    fun onPersist() {
        createdAt = LocalDateTime.now()
        modifiedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        modifiedAt = LocalDateTime.now()
    }
}
