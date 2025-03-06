package com.example.domain.models.restaurantes

import kotlinx.serialization.Serializable

@Serializable
data class UpdateRestaurante (
    val id: Int? = null,
    var titulo: String? = null,
    var descripcion: String? = null,
    var imagen: String? = null
)