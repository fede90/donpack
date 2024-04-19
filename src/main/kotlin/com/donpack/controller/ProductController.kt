package com.donpack.controller

import com.donpack.config.toUser
import com.donpack.dto.ItemDto
import com.donpack.dto.ProductDto
import com.donpack.dto.toDto
import com.donpack.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/public/products")
class ProductController(val productService: ProductService) {

    @GetMapping
    fun getProducts(): List<ProductDto> {

        return productService.getAllProducts()
    }

}