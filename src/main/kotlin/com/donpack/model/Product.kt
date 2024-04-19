package com.donpack.model

import jakarta.persistence.*

@Entity(name = "productos")
data class Product(
        @Id
        @GeneratedValue()
        var id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "nombre_id")
        var name: ProductName,

        @ManyToOne
        @JoinColumn(name = "medida_id")
        var measure: ProductMeasure,

        @ManyToOne
        @JoinColumn(name = "color_id")
        var color: ProductColor,

        @Column(name = "hay_stock")
        var hasStock: Boolean,

        @Column
        var stock: Long
)
