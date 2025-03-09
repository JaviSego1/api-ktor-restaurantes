package com.example.domain.models.restaurantes

import kotlinx.serialization.Serializable

@Serializable
data class Restaurante(
    val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val imagen: String? = null
)