package com.mak.knote.backend.feature.notes

import com.mak.knote.backend.di.domain.IDomainProvider
import com.mak.knote.backend.util.KnoteConstants.NOTES_ROUTE
import com.mak.knote.backend.util.KnoteConstants.PAGINATION_LIMIT
import com.mak.knote.backend.util.getUserIdFromToken
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

internal fun Routing.noteRoutes(domainProvider: IDomainProvider) {

    authenticate {
        get(NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: PAGINATION_LIMIT
            val response = domainProvider.provideGetNoteUseCase().invoke(Triple(userID, page, limit))
            call.respond(response.statusCode, response)
        }
    }
}