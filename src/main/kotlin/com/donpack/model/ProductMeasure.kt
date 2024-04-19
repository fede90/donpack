package com.donpack.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "producto_medidas")
data class ProductMeasure(
    @Id
    var id: Long,
    @Column(name = "medidas")
    var measure: String,
    @Column(name = "comentarios")
    var description: String
)