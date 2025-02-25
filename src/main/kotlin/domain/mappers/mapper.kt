package com.example.domain.mappers

import com.example.data.persistence.models.restaurantes.RestauranteDao
import com.example.data.persistence.models.usuarios.UsuarioDao
import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.models.usuarios.Usuario

fun RestauranteDaoToRestaurante (restauranteDao: RestauranteDao) : Restaurante {
    val restaurante = Restaurante(
        restauranteDao.titulo,
        restauranteDao.descripcion
    )

    return restaurante
}

fun UsuarioDaoToUsuario (usuarioDao: UsuarioDao) : Usuario {
    val usuario = Usuario(
        usuarioDao.name,
        usuarioDao.dni,
        usuarioDao.email,
        usuarioDao.password,
        usuarioDao.token
    )

    return usuario
}