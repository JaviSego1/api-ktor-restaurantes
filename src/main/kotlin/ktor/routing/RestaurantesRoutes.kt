package ktor.routing

import com.example.domain.models.restaurantes.Restaurante
import domain.usecase.AddRestauranteUseCase
import domain.usecase.DeleteRestauranteUseCase
import domain.usecase.GetAllRestaurantesUseCase
import domain.usecase.UpdateRestauranteUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.restaurantesRoutes(getAllRestaurantesUseCase: GetAllRestaurantesUseCase, addRestauranteUseCase: AddRestauranteUseCase,
                             updateRestauranteUseCase: UpdateRestauranteUseCase, deleteRestauranteUseCase: DeleteRestauranteUseCase) {

    route("/restaurantes") {
        get {
            val restaurantes = getAllRestaurantesUseCase()
            call.respond(restaurantes)
        }

        post("/add") {
            val request = call.receive<Restaurante>()
            val review = addRestauranteUseCase(request.titulo, request.descripcion)
            if (review != null) {
                call.respond(HttpStatusCode.Created, "Restaurante creado correctamente")
            } else {
                call.respond(HttpStatusCode.BadRequest, "Restaurante no creado")
            }
        }

        patch("/edit/{id}") {
            val id = call.parameters["id"]
            val review = call.receive<Restaurante>()
            id?.let {
                val request = updateRestauranteUseCase(id.toInt(), review)
                if (!request) {
                    call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                } else {
                    call.respond(HttpStatusCode.OK, "Restaurante editado correctamente")
                }
            }?:run {
                call.respond(HttpStatusCode.NoContent, "ID no encontrado")
            }
        }

        delete("/del/{id}") {
            val id = call.parameters["id"]
            id?.let {
                val request = deleteRestauranteUseCase(id.toInt())
                if (!request) {
                    call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                } else {
                    call.respond(HttpStatusCode.OK, "Restaurante eliminado correctamente")
                }
            }?:run {
                call.respond(HttpStatusCode.NoContent, "ID no encontrado")
            }
        }
    }
}