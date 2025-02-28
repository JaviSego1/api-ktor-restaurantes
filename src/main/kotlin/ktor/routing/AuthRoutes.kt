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
            val loggedInUsuario = loginUseCase(usuario.name, usuario.password)
            if (loggedInUsuario != null) {
                call.respond(HttpStatusCode.OK, "Has iniciado sesión con éxito")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
            }
        }
    }
}