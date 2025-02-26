package com.example.data.persistence.repository

import com.example.data.persistence.models.usuarios.UsuarioDao
import com.example.data.persistence.suspendTransaction
import com.example.domain.mappers.toUsuario
import com.example.domain.models.usuarios.UpdateUsuario
import com.example.domain.models.usuarios.Usuario
import com.example.domain.repository.UsuarioInterface

class PersistenceUsuarioRepository: UsuarioInterface {

    override suspend fun getAllUsuarios(): List<Usuario> {
        return suspendTransaction {
            UsuarioDao.all().map { it.toUsuario() }
        }
    }

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

