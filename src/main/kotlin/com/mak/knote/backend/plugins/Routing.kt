package com.mak.knote.backend.plugins

import com.mak.knote.backend.di.domain.DomainLocator
import com.mak.knote.backend.feature.auth.authRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

internal val domainLocator = DomainLocator
internal fun Application.configureRouting() {
    routing {
        authRoutes(domainLocator.provideDomainProvider())
    }
}
