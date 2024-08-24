package com.mak.knote.backend.plugins

import com.mak.knote.backend.feature.auth.authRoutes
import com.mak.knote.backend.feature.notes.noteRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

internal fun Application.configureRouting() {
    routing {
        authRoutes()
        noteRoutes()
    }
}
