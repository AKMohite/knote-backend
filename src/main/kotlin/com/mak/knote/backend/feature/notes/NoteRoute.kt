package com.mak.knote.backend.feature.notes

import com.mak.knote.backend.base.PaginatedResponse
import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.feature.notes.repository.INotesRepository
import com.mak.knote.backend.util.KnoteConstants
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
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import org.koin.ktor.ext.inject

internal fun Routing.noteRoutes() {

    val notesRepo by inject<INotesRepository>()
    val exceptionHandler by inject<IExceptionHandler>()

    authenticate(KnoteConstants.JWT_NAME) {
        get(NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: PAGINATION_LIMIT
            val response = notesRepo.getNotesForUser(userID, page, limit)
            val paginatedResponse = (response as? PaginatedResponse)?.paginatedResponse ?: ""
            call.respond(response.statusCode, paginatedResponse)
        }

        post(NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val request =
                call.receiveNullable<NoteDTO>() ?: throw exceptionHandler.respondWithBadRequestException("Bad request")
            val response = notesRepo.createNote(userID, request)
            call.respond(response.statusCode, response)
        }

        put(SINGLE_NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val noteId = call.parameters[NOTEID] ?: throw exceptionHandler.respondWithBadRequestException("Bad request")
            val request =
                call.receiveNullable<NoteDTO>() ?: throw exceptionHandler.respondWithBadRequestException("Bad request")
            val response = notesRepo.updateNote(userID, noteId, request)
            call.respond(response.statusCode, response)
        }

        delete(SINGLE_NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val noteId = call.parameters[NOTEID] ?: throw exceptionHandler.respondWithBadRequestException("Bad request")
            val response = notesRepo.deleteNote(userID, noteId)
            call.respond(response.statusCode, response)
        }
    }
}
