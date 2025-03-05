package com.example.data.persistence.models.usuarios

import com.example.data.persistence.models.restaurantes.RestauranteTable.nullable
import org.jetbrains.exposed.dao.id.IntIdTable

object UsuarioTable: IntIdTable("Usuarios") {
    val name = varchar("name", 80)
    val email = varchar("email", 100).uniqueIndex()
    val password = varchar("password", 255)
    val token = varchar("token", 255).nullable()
}