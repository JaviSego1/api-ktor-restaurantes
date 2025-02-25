package com.example.domain.repository

import com.example.domain.models.usuarios.UpdateUsuario
import com.example.domain.models.usuarios.Usuario

interface UsuarioInterface {

    suspend fun getUsuarioByDni(dni: String): Usuario?
    suspend fun login(dni: String, password: String): Boolean
    suspend fun register(usuario: UpdateUsuario): Usuario?
}