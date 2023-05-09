package com.mak.knote.backend.feature.auth

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Routing.authRoutes() {
    get("/") {
        call.respondText("̵̵Hello World!")
    }
}