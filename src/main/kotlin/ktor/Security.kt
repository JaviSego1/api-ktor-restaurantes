package ktor

import domain.security.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSecurity() {
    install(Authentication) {
        jwt("jwt-auth") {
            JwtConfig.configureAuthentication(this)
        }
    }

    routing {
        authenticate("jwt-auth") {

            get("/protected") {
                val principal = call.principal<JWTPrincipal>()
                val name = principal?.payload?.getClaim("name")?.asString()
                call.respondText("Hello, $name! You are authenticated.")
            }
        }
    }
}