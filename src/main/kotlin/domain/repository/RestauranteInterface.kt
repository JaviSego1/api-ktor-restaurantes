package com.example.domain.repository

import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.models.restaurantes.UpdateRestaurante

interface RestauranteInterface {

    suspend fun getAllRestaurantes(): List<Restaurante>
    suspend fun addRestaurante(restaurante: Restaurante) : Boolean
    suspend fun updateRestaurante(restaurante: UpdateRestaurante, nombreNuevo: String): Boolean
    suspend fun deleteRestaurante(nombreRestaurante: String): Boolean


}