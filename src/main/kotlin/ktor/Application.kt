package com.example.ktor

import com.example.data.persistence.repository.PersistenceRestauranteRepository
import com.example.data.persistence.repository.PersistenceUsuarioRepository
import com.example.domain.repository.UsuarioInterface
import io.ktor.server.application.*
import ktor.configureDatabases
import domain.usecase.LoginUseCase
import domain.usecase.RegistroUseCase
import ktor.configureSecurity

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    configureSecurity()
    configureSerialization()
    configureDatabases()

    val usuarioInterface: UsuarioInterface = PersistenceUsuarioRepository()
    val restauranteInterface = PersistenceRestauranteRepository()

    val registroUseCase = RegistroUseCase(usuarioInterface)
    val loginUseCase = LoginUseCase(usuarioInterface)

    configureRouting(registroUseCase, loginUseCase, restauranteInterface, usuarioInterface)
}
