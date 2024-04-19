package com.donpack.dto

import com.donpack.model.ProductColor
import com.donpack.model.ProductMeasure
import com.donpack.model.ProductName
import org.springframework.web.server.ResponseStatusException

/**
 * This file contains all outgoing DTOs.
 * [ApiException] is used to easily throw exceptions.
 */
class ApiException(code: Int, message: String) : ResponseStatusException(code, message, null)

data class LoginResponseDto(
    val token: String,
)

data class ItemDto(
    val id: Long,
    val name: String,
    val count: Int,
    val note: String?,
)


data class ProductDto(
    val id: Long,
    val name: ProductName,
    val measure: ProductMeasure,
    val color: ProductColor,
    val hasStock: Boolean,
    val stock: Long
)
