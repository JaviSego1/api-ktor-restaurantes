package com.example.data.persistence.models.usuarios

import com.example.domain.models.usuarios.Usuario
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UsuarioDao(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<UsuarioDao>(UsuarioTable)
    var name by UsuarioTable.name
    var email by UsuarioTable.email
    var password by UsuarioTable.password
    var token by UsuarioTable.token

    fun toUsuario(): Usuario {
        return Usuario(
            id = id.value,
            name = name,
            email = email,
            password = password,
            token = token
        )
    }
}