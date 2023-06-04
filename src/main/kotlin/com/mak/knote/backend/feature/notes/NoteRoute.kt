package com.mak.knote.backend.feature.notes

import com.mak.knote.backend.base.AuthorizationException
import com.mak.knote.backend.base.BadRequestException
import com.mak.knote.backend.di.domain.IDomainProvider
import com.mak.knote.backend.util.KnoteConstants.NOTEID
import com.mak.knote.backend.util.KnoteConstants.NOTES_ROUTE
import com.mak.knote.backend.util.KnoteConstants.PAGINATION_LIMIT
import com.mak.knote.backend.util.KnoteConstants.SINGLE_NOTES_ROUTE
import com.mak.knote.backend.util.getUserIdFromToken
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put

internal fun Routing.noteRoutes(domainProvider: IDomainProvider) {

    authenticate {
        get(NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: PAGINATION_LIMIT
            val response = domainProvider.provideGetNoteUseCase().invoke(Triple(userID, page, limit))
            call.respond(response.statusCode, response)
        }

        post(NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val request = kotlin.runCatching { call.receiveNullable<NoteDTO>() }.getOrNull() ?: kotlin.run {
                throw BadRequestException("Bad request")
            }

            val response = domainProvider.provideCreateNoteUseCase().invoke(Pair(userID, request))
            call.respond(response.statusCode, response)
        }

        put(SINGLE_NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val noteId = call.parameters[NOTEID] ?: throw AuthorizationException("Bad call")
            val request = kotlin.runCatching { call.receiveNullable<NoteDTO>() }.getOrNull() ?: kotlin.run {
                throw BadRequestException("Bad request")
            }
            val response = domainProvider.provideUpdateNoteUseCase().invoke(Triple(userID, noteId, request))
            call.respond(response.statusCode, response)
        }
    }
}