package com.example.ktor

import domain.usecase.GetAllRestaurantesUseCase
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(getAllRestaurantesUseCase: GetAllRestaurantesUseCase) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/restaurantes") {
            try {
                val restaurantes = getAllRestaurantesUseCase() // Esto es una función suspendida
                call.respond(restaurantes)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error obteniendo los restaurantes")
            }
        }



    }
}
