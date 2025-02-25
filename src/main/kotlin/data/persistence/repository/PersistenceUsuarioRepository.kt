package com.example.data.persistence.repository

import com.example.domain.models.usuarios.UpdateUsuario
import com.example.domain.models.usuarios.Usuario
import com.example.domain.repository.UsuarioInterface

class PersistenceUsuarioRepository: UsuarioInterface {
    override suspend fun getUsuarioByDni(dni: String): Usuario? {
        TODO("Not yet implemented")
    }

    override suspend fun login(dni: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun register(usuario: UpdateUsuario): Usuario? {
        TODO("Not yet implemented")
    }

}