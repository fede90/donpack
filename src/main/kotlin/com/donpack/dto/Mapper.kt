package com.donpack.dto

import com.donpack.model.Item
import com.donpack.model.Product

/**
 * This file contains all mapping extension methods for DTOs.
 * In this simple example, there is only [Item] and [ItemDto].
 */
fun Item.toDto(): ItemDto {
    return ItemDto(id, name, count, note)
}


fun Product.toDto(): ProductDto {
    return ProductDto(id!!, name,measure, color, hasStock, stock)
}
