package com.example.domain.models.restaurantes

import kotlinx.serialization.Serializable

@Serializable
data class UpdateRestaurante (
    var titulo: String? = null,
    var descripcion: String? = null
)