package ktor.routing

import com.example.domain.models.restaurantes.Restaurante
import domain.usecase.AddRestauranteUseCase
import domain.usecase.DeleteRestauranteUseCase
import domain.usecase.GetAllRestaurantesUseCase
import domain.usecase.UpdateRestauranteUseCase
import com.example.domain.repository.UsuarioInterface // Usamos el repositorio correcto
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.restaurantesRoutes(
    getAllRestaurantesUseCase: GetAllRestaurantesUseCase,
    addRestauranteUseCase: AddRestauranteUseCase,
    updateRestauranteUseCase: UpdateRestauranteUseCase,
    deleteRestauranteUseCase: DeleteRestauranteUseCase,
    usuarioInterface: UsuarioInterface // Usamos el repositorio de UsuarioInterface
) {

    route("/restaurantes") {
        authenticate("jwt-auth") {
            get {
                val principal = call.principal<JWTPrincipal>()
                val email = principal?.payload?.getClaim("email")?.asString()

                if (email != null) {
                    val storedToken = usuarioInterface.getTokenByUsername(email)
                    val providedToken = call.request.headers["Authorization"]?.removePrefix("Bearer ")

                    if (storedToken == providedToken) {
                        val restaurantes = getAllRestaurantesUseCase()
                        call.respond(restaurantes)
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                    }
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                }
            }

            post("/add") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal?.payload?.getClaim("email")?.asString()

                if (email != null) {
                    // Verificar que el token coincida con el almacenado en la base de datos
                    val storedToken = usuarioInterface.getTokenByUsername(email)
                    val providedToken = call.request.headers["Authorization"]?.removePrefix("Bearer ")

                    if (storedToken == providedToken) {
                        val request = call.receive<Restaurante>()
                        val review = addRestauranteUseCase(request.titulo, request.descripcion)
                        if (review != null) {
                            call.respond(HttpStatusCode.Created, "Restaurante creado correctamente")
                        } else {
                            call.respond(HttpStatusCode.BadRequest, "Restaurante no creado")
                        }
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                    }
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                }
            }

            patch("/edit/{id}") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal?.payload?.getClaim("email")?.asString()

                if (email != null) {
                    val storedToken = usuarioInterface.getTokenByUsername(email)
                    val providedToken = call.request.headers["Authorization"]?.removePrefix("Bearer ")

                    if (storedToken == providedToken) {
                        val id = call.parameters["id"]
                        val review = call.receive<Restaurante>()
                        id?.let {
                            val request = updateRestauranteUseCase(id.toInt(), review)
                            if (!request) {
                                call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                            } else {
                                call.respond(HttpStatusCode.OK, "Restaurante editado correctamente")
                            }
                        } ?: run {
                            call.respond(HttpStatusCode.NoContent, "ID no encontrado")
                        }
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                    }
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                }
            }

            delete("/del/{id}") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal?.payload?.getClaim("email")?.asString()

                if (email != null) {
                    // Verificar que el token coincida con el almacenado en la base de datos
                    val storedToken = usuarioInterface.getTokenByUsername(email)
                    val providedToken = call.request.headers["Authorization"]?.removePrefix("Bearer ")

                    if (storedToken == providedToken) {
                        val id = call.parameters["id"]
                        id?.let {
                            val request = deleteRestauranteUseCase(id.toInt())
                            if (!request) {
                                call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                            } else {
                                call.respond(HttpStatusCode.NoContent)
                            }
                        } ?: run {
                            call.respond(HttpStatusCode.BadRequest, "ID no encontrado")
                        }
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                    }
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token")
                }
            }
        }
    }
}
