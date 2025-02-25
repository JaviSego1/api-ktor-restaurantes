package com.example.ktor

import io.ktor.server.application.*
import ktor.configureDatabases

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureDatabases()
}
