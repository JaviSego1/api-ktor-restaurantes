package ktor.routing

import com.example.domain.models.usuarios.Usuario
import domain.usecase.LoginUseCase
import domain.usecase.RegistroUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes(registroUseCase: RegistroUseCase, loginUseCase: LoginUseCase) {

    route("/usuarios") {
        post("/registro") {
            val usuario = call.receive<Usuario>()
            val createdUsuario = registroUseCase(usuario)
            call.respond(HttpStatusCode.Created, createdUsuario)
        }

        post("/login") {
            val usuario = call.receive<Usuario>()
            val token = loginUseCase(usuario.name, usuario.password)
            if (token != null) {
                // Aquí devolvemos el token al usuario
                call.respond(HttpStatusCode.OK, mapOf("message" to "Has iniciado sesión con éxito", "token" to token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
            }
        }
    }
}