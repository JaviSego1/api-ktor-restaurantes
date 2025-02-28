package com.example.domain.models.usuarios

import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val name: String,
    val email: String,
    val password: String
)