package com.example.domain.mappers

import com.example.data.persistence.models.restaurantes.RestauranteDao
import com.example.data.persistence.models.usuarios.UsuarioDao
import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.models.restaurantes.UpdateRestaurante
import com.example.domain.models.usuarios.UpdateUsuario
import com.example.domain.models.usuarios.Usuario

fun Usuario.toUpdateUsuario() : UpdateUsuario {
    return UpdateUsuario(
        name = name,
        email = email,
        password = password,
        token = token
    )
}

fun UpdateUsuario.toUsuario() : Usuario {
    return Usuario(
        name = name!!,
        email = email!!,
        password = password!!,
        token = token!!
    )
}

//fun UsuarioDao.toUsuario () : Usuario {
//    val e = Usuario (
//        this.name,
//        this.email,
//        this.password,
//        this.token
//    )
//
//    return e
//}


fun Restaurante.toUpdateRestaurante() : UpdateRestaurante {
    return UpdateRestaurante(
        id = id,
        titulo = titulo,
        descripcion = descripcion,
        imagen = imagen
    )
}

fun UpdateRestaurante.toRestaurante() : Restaurante {
    return Restaurante(
        id = id!!,
        titulo = titulo!!,
        descripcion = descripcion!!,
        imagen = imagen!!
    )
}


