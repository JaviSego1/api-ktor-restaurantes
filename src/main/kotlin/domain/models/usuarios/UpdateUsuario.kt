package com.example.domain.models.usuarios

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUsuario(
    val id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var token: String? = null
)