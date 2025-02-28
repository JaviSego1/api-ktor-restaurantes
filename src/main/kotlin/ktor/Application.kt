package com.example.ktor

import com.example.data.persistence.repository.PersistenceRestauranteRepository
import com.example.data.persistence.repository.PersistenceUsuarioRepository
import com.example.domain.repository.RestauranteInterface
import com.example.domain.repository.UsuarioInterface
import io.ktor.server.application.*
import ktor.configureDatabases
import domain.usecase.GetAllRestaurantesUseCase
import domain.usecase.LoginUseCase
import domain.usecase.RegistroUseCase

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    configureSerialization()
    configureDatabases()

    val restauranteInterface = PersistenceRestauranteRepository()
    val usuarioInterface = PersistenceUsuarioRepository()
    val registroUseCase = RegistroUseCase(usuarioInterface)
    val loginUseCase = LoginUseCase(usuarioInterface)

    configureRouting(registroUseCase, loginUseCase, restauranteInterface)
}
