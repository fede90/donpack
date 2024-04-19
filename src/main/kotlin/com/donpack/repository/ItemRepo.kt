package com.donpack.repository

import com.donpack.model.Item
import com.donpack.model.User
import org.springframework.data.repository.CrudRepository

interface ItemRepo : CrudRepository<Item, Long> {
    fun findByUser(user: User): List<Item>
    fun findByNameAndUser(name: String, user: User): Item?

    fun existsByNameAndUser(name: String, user: User): Boolean
}
