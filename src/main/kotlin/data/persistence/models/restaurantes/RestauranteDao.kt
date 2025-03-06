package com.example.data.persistence.models.restaurantes

import com.example.data.persistence.models.restaurantes.RestauranteTable
import com.example.domain.models.restaurantes.Restaurante
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class RestauranteDao(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<RestauranteDao>(RestauranteTable)

    var titulo by RestauranteTable.titulo
    var descripcion by RestauranteTable.descripcion
    var imagen by RestauranteTable.imagen

    fun toRestaurante(): Restaurante {
        return Restaurante(
            id = id.value,
            titulo = titulo,
            descripcion = descripcion,
            imagen = imagen
        )
    }
}