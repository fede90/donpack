package com.donpack.repository

import com.donpack.model.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepo: CrudRepository<Product, Long>  {
    fun findByNameId(id: Long): List<Product>
}