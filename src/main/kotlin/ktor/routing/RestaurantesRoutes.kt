package ktor.routing

import com.example.domain.models.restaurantes.Restaurante
import domain.usecase.AddRestauranteUseCase
import domain.usecase.DeleteRestauranteUseCase
import domain.usecase.GetAllRestaurantesUseCase
import domain.usecase.UpdateRestauranteUseCase
import com.example.domain.repository.UsuarioInterface
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.util.*

fun Route.restaurantesRoutes(
    getAllRestaurantesUseCase: GetAllRestaurantesUseCase,
    addRestauranteUseCase: AddRestauranteUseCase,
    updateRestauranteUseCase: UpdateRestauranteUseCase,
    deleteRestauranteUseCase: DeleteRestauranteUseCase,
    usuarioInterface: UsuarioInterface
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
                    val storedToken = usuarioInterface.getTokenByUsername(email)
                    val providedToken = call.request.headers["Authorization"]?.removePrefix("Bearer ")

                    if (storedToken == providedToken) {
                        val request = call.receive<Restaurante>()
                        val uploadDir = "res/drawable"
                        File(uploadDir).mkdirs()

                        val imageName = request.imagen?.let { saveImageFromBase64(it, uploadDir) } ?: ""
                        val restaurante = addRestauranteUseCase(request.titulo, request.descripcion, imageName)

                        if (restaurante != null) {
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
                        val request = call.receive<Restaurante>()
                        val uploadDir = "res/drawable"

                        id?.let {
                            val existingRestaurante = getAllRestaurantesUseCase().find { it.id == id.toInt() }
                            if (existingRestaurante != null) {
                                deleteImage(existingRestaurante.imagen, uploadDir)
                                val imageName = request.imagen?.let { saveImageFromBase64(it, uploadDir) }
                                val updatedRestaurante = request.copy(imagen = imageName ?: existingRestaurante.imagen)
                                val success = updateRestauranteUseCase(id.toInt(), updatedRestaurante)

                                if (!success) {
                                    call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                                } else {
                                    call.respond(HttpStatusCode.OK, "Restaurante editado correctamente")
                                }
                            } else {
                                call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
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

            delete("/del/{id}") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal?.payload?.getClaim("email")?.asString()

                if (email != null) {
                    val storedToken = usuarioInterface.getTokenByUsername(email)
                    val providedToken = call.request.headers["Authorization"]?.removePrefix("Bearer ")

                    if (storedToken == providedToken) {
                        val id = call.parameters["id"]
                        val uploadDir = "res/drawable"

                        id?.let {
                            val existingRestaurante = getAllRestaurantesUseCase().find { it.id == id.toInt() }
                            if (existingRestaurante != null) {
                                deleteImage(existingRestaurante.imagen, uploadDir)
                                val success = deleteRestauranteUseCase(id.toInt())
                                if (!success) {
                                    call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
                                } else {
                                    call.respond(HttpStatusCode.NoContent)
                                }
                            } else {
                                call.respond(HttpStatusCode.NotFound, "Restaurante no encontrado")
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

fun saveImageFromBase64(base64Image: String, uploadDir: String): String {
    val imageBytes = Base64.getDecoder().decode(base64Image)
    val imageName = "a" + UUID.randomUUID().toString().replace("-", "") + ".jpg"
    val imageFile = File(uploadDir, imageName)
    imageFile.writeBytes(imageBytes)
    return imageName
}

fun deleteImage(imageName: String?, uploadDir: String) {
    if (imageName != null) {
        val imageFile = File(uploadDir, imageName)
        if (imageFile.exists()) {
            imageFile.delete()
        }
    }
}
