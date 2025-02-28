package com.example.domain.repository

import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.models.restaurantes.UpdateRestaurante

interface RestauranteInterface {

    suspend fun getAllRestaurantes(): List<Restaurante>
    suspend fun addRestaurante(restaurante: Restaurante) : Restaurante
    suspend fun updateRestaurante(id: Int, restaurante: Restaurante): Boolean
    suspend fun deleteRestaurante(id: Int): Boolean


}