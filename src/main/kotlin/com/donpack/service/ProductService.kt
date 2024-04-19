package com.donpack.service

import com.donpack.dto.ProductDto
import com.donpack.dto.toDto
import com.donpack.repository.ProductRepo
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepo: ProductRepo) {

    fun getAllProducts(): List<ProductDto> {
        return productRepo.findByNameId(1L).map { item -> item.toDto() }
    }

}