package com.example.data.persistence.repository

import com.example.data.persistence.models.usuarios.UsuarioDao
import com.example.data.persistence.models.usuarios.UsuarioTable
import com.example.data.persistence.suspendTransaction
import com.example.domain.mappers.toUsuario
import com.example.domain.models.usuarios.UpdateUsuario
import com.example.domain.models.usuarios.Usuario
import com.example.domain.repository.UsuarioInterface

class PersistenceUsuarioRepository: UsuarioInterface {
    override suspend fun createUsuario(usuario: Usuario): Usuario {
        return suspendTransaction {
            UsuarioDao.new {
                name = usuario.name
                email = usuario.email
                password = usuario.password
            }.toUsuario()
        }
    }

    override suspend fun getUsuarioByUsername(name: String): Usuario? {
        return suspendTransaction {
            UsuarioDao.find {
                (UsuarioTable.name eq name)
            }.firstOrNull()?.toUsuario()
        }
    }

    override suspend fun updateUserToken(userId: Int, token: String): Boolean {
        return suspendTransaction {
            val user = UsuarioDao.findById(userId)
            user?.let {
                it.token = token
                true
            } ?: false
        }
    }

    override suspend fun getTokenByUsername(email: String): String? {
        return suspendTransaction {
            UsuarioDao.find { UsuarioTable.email eq email }
                .firstOrNull()
                ?.token
        }
    }


}

