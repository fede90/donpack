package com.donpack.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "producto_colores")
data class ProductColor(
    @Id
    var id: Long,
    @Column(name = "color")
    var name: String
)
