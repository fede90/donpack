package com.donpack.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "producto_nombre")
data class ProductName(
    @Id
    var id: Long,
    @Column(name = "nombre")
    var name: String,
    @Column(name = "comentarios")
    var description: String
)
