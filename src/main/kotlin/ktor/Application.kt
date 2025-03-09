package com.example.ktor

import com.example.data.persistence.repository.PersistenceRestauranteRepository
import com.example.data.persistence.repository.PersistenceUsuarioRepository
import com.example.domain.repository.UsuarioInterface
import io.ktor.server.application.*
import ktor.configureDatabases
import domain.usecase.LoginUseCase
import domain.usecase.RegistroUseCase
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktor.configureSecurity
import java.io.File

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

    routing {
        get("/images/{imageName}") {
            val imageName = call.parameters["imageName"]
            if (imageName != null) {
                val imageFile = File("res/drawable/$imageName")
                if (imageFile.exists()) {
                    call.respondFile(imageFile)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Image not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Image name is missing")
            }
        }
    }
}
