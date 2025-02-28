package com.example.data.persistence.repository


import com.example.data.persistence.models.restaurantes.RestauranteDao
import com.example.data.persistence.models.restaurantes.RestauranteTable
import com.example.data.persistence.suspendTransaction
import com.example.domain.mappers.toRestaurante
import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.repository.RestauranteInterface
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class PersistenceRestauranteRepository: RestauranteInterface{
    override suspend fun getAllRestaurantes(): List<Restaurante> {
        return suspendTransaction {
            RestauranteDao.all().map { it.toRestaurante() }
        }
    }


    override suspend fun addRestaurante(restaurante: Restaurante): Restaurante {
        return suspendTransaction {
            RestauranteDao.new {
                titulo = restaurante.titulo
                descripcion = restaurante.descripcion
            }.toRestaurante()
        }
    }

    override suspend fun updateRestaurante(id: Int, restaurante: Restaurante): Boolean {
        return suspendTransaction {
            val res = RestauranteTable.update({RestauranteTable.id eq id}) {
                it[titulo]= restaurante.titulo
                it[descripcion] = restaurante.descripcion
            }
            res > 0
        }
    }

    override suspend fun deleteRestaurante(id: Int): Boolean {
        return suspendTransaction {
            val deletedRestaurante = RestauranteTable.deleteWhere{RestauranteTable.id eq id}
            deletedRestaurante == 1
        }
    }


}