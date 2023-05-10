package com.mak.knote.backend.feature.auth

import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

internal fun Routing.authRoutes() {
    get("/") {
        call.respondText("̵̵Hello World!")
    }
}