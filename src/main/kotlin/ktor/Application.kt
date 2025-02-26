package com.example.ktor

import com.example.data.persistence.repository.PersistenceRestauranteRepository
import com.example.domain.repository.RestauranteInterface
import io.ktor.server.application.*
import ktor.configureDatabases
import domain.usecase.GetAllRestaurantesUseCase

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val RestauranteInterface: RestauranteInterface = PersistenceRestauranteRepository() // Instancia del repositorio
    val getAllRestaurantesUseCase = GetAllRestaurantesUseCase(RestauranteInterface)

    configureSerialization()
    configureRouting(getAllRestaurantesUseCase) // Pasamos el UseCase
    configureDatabases()
}
