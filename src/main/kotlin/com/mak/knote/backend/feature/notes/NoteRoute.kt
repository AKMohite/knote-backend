package com.mak.knote.backend.feature.notes

/*
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

        delete(SINGLE_NOTES_ROUTE) {
            val userID = getUserIdFromToken()
            val noteId = call.parameters[NOTEID] ?: throw AuthorizationException("Bad call")
            val response = domainProvider.provideDeleteNoteUseCase().invoke(Pair(userID, noteId))
            call.respond(response.statusCode, response)
        }
    }
}*/
