package com.example.data.persistence.repository

import com.example.data.persistence.models.restaurantes.RestauranteDao
import com.example.data.persistence.models.restaurantes.RestauranteTable
import com.example.data.persistence.suspendTransaction
import com.example.domain.mappers.RestauranteDaoToRestaurante
import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.models.restaurantes.UpdateRestaurante
import com.example.domain.repository.RestauranteInterface
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PersistenceRestauranteRepository: RestauranteInterface{
    override suspend fun getAllRestaurantes(): List<Restaurante> {
        return suspendTransaction {
            RestauranteDao.all().map(::RestauranteDaoToRestaurante)
        }
    }

    override suspend fun addRestaurante(restaurante: Restaurante): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateRestaurante(restaurante: UpdateRestaurante, nombreNuevo: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRestaurante(nombreRestaurante: String): Boolean {
        var fila = 0

        try{
            suspendTransaction {
                fila = RestauranteTable.deleteWhere { RestauranteTable.titulo eq nombreRestaurante }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return fila == 1
    }

}