package com.example.domain.models.usuarios

import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val token: String? = null
)