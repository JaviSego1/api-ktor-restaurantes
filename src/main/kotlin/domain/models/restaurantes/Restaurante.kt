package com.example.domain.models.restaurantes

import kotlinx.serialization.Serializable

@Serializable
data class Restaurante(
    val id: Int? = null,
    val titulo: String,
    val descripcion: String
)