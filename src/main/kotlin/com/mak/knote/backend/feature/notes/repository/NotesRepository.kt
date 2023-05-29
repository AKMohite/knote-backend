package com.mak.knote.backend.feature.notes.repository

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.PaginatedResponse
import com.mak.knote.backend.base.http.ExceptionHandler
import com.mak.knote.backend.feature.notes.NoteDTO
import com.mak.knote.backend.feature.notes.service.INotesApiService
import com.mak.knote.backend.feature.notes.toNoteDTO
import io.ktor.http.HttpStatusCode

internal class NotesRepository(
    private val notesApiService: INotesApiService,
    private val exceptionHandler: ExceptionHandler
) : INotesRepository {

    private companion object {
        private const val PLEASE_CHECK_THE_PARAMS = "Please check the query params"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val ALREADY_LIKED = "Already liked"
        private const val POST_NOT_FOUND = "Post not found"
        private const val ZERO = 0
        private const val ONE = 1
    }

    override suspend fun getNotesForUser(userId: String, page: Int, limit: Int): BaseResponse<List<NoteDTO>> {
        if (page > ZERO && limit > ZERO) {
            val (notes, totalCount) = notesApiService.getNotesForUser(userId, page, limit)
            val response: List<NoteDTO> = notes.map {
                it.toNoteDTO()
            }
            val totalPages = (totalCount.div(limit)).plus(ONE)
            val next = if (response.count() == limit) page.plus(ONE) else null
            val prev = if (page > ONE) page.minus(ONE) else null
            return PaginatedResponse(statusCode = HttpStatusCode.OK, prev, next, totalCount, totalPages, response)
        } else {
            throw exceptionHandler.respondWithGenericException(PLEASE_CHECK_THE_PARAMS)
        }
    }

}
