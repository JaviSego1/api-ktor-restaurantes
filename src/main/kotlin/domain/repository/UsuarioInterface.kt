package com.example.domain.repository

import com.example.domain.models.usuarios.UpdateUsuario
import com.example.domain.models.usuarios.Usuario

interface UsuarioInterface {

    suspend fun createUsuario(usuario: Usuario): Usuario
    suspend fun getUsuarioByUsername(name: String): Usuario?
    suspend fun updateUserToken(userId: Int, token: String): Boolean
    suspend fun getTokenByUsername(email: String): String?
}