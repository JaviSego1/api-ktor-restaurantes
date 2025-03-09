package com.example.ktor

import com.example.domain.repository.RestauranteInterface
import com.example.domain.repository.UsuarioInterface
import domain.usecase.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktor.routing.authRoutes
import ktor.routing.restaurantesRoutes

fun Application.configureRouting(registroUseCase: RegistroUseCase, loginUseCase: LoginUseCase,
                                 restauranteInterface: RestauranteInterface, usuarioInterface: UsuarioInterface
) {

    val getAllRestaurantesUseCase = GetAllRestaurantesUseCase(restauranteInterface)
    val addRestauranteUseCase = AddRestauranteUseCase(restauranteInterface)
    val updateRestauranteUseCase = UpdateRestauranteUseCase(restauranteInterface)
    val deleteRestauranteUseCase = DeleteRestauranteUseCase(restauranteInterface)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        staticResources("/static", "static")

        authRoutes(registroUseCase, loginUseCase)

        restaurantesRoutes(
            getAllRestaurantesUseCase,
            addRestauranteUseCase,
            updateRestauranteUseCase,
            deleteRestauranteUseCase,
            usuarioInterface
        )

    }
}




