package com.example.domain.models.usuarios

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUsuario(
    var name: String? = null,
    var email: String? = null,
    var password: String? = null
)