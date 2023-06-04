package com.mak.knote.backend.feature.notes.repository

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.PaginatedResponse
import com.mak.knote.backend.base.SuccessResponse
import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.feature.notes.Note
import com.mak.knote.backend.feature.notes.NoteDTO
import com.mak.knote.backend.feature.notes.service.INotesApiService
import com.mak.knote.backend.feature.notes.toNoteDTO
import io.ktor.http.HttpStatusCode
import java.util.*

internal class NotesRepository(
    private val notesApiService: INotesApiService,
    private val exceptionHandler: IExceptionHandler
) : INotesRepository {

    private companion object {
        private const val PLEASE_CHECK_THE_PARAMS = "Please check the query params"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val ZERO = 0
        private const val ONE = 1
    }

    override suspend fun createNote(userId: String, note: NoteDTO): BaseResponse<NoteDTO> {
        if (userId.isNotBlank()) {
            val noteToAdd = Note(
                title = note.title,
                description = note.description,
                color = note.color,
                createdBy = userId,
                isDeleted = false,
                createdAt = Date().toInstant().toString(),
                updatedAt = Date().toInstant().toString()
            )
            val createdNote = notesApiService.insertNote(userId, noteToAdd)

            if (createdNote) {
                val addedNote = with(noteToAdd) {
                    NoteDTO(
                        id = id,
                        title = title,
                        description = description,
                        color = color,
//                        createdBy = createdBy,
                        isDeleted = isDeleted,
                        createdAt = createdAt,
                        updatedAt = updatedAt
                    )
                }
                return SuccessResponse(statusCode = HttpStatusCode.Created, addedNote)
            } else {
                throw exceptionHandler.respondWithSomethingWentWrongException()
            }
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        }
    }

    override suspend fun getNotesForUser(userId: String, page: Int, limit: Int): BaseResponse<List<NoteDTO>> {
        if (page > ZERO && limit > ZERO) {
            val (notes, totalCount) = notesApiService.getNotesForUser(userId, page, limit)
            val response: List<NoteDTO> = notes.map {
                it.toNoteDTO()
            }
            val divCount = totalCount.div(limit)
            val remainingModDocuments = totalCount.mod(limit)
            val totalPages = if (remainingModDocuments != 0) divCount.plus(ONE) else divCount
            val next = if (response.count() == limit) page.plus(ONE) else null
            val prev = if (page > ONE) page.minus(ONE) else null
            return PaginatedResponse(statusCode = HttpStatusCode.OK, prev, next, totalCount, totalPages, response)
        } else {
            throw exceptionHandler.respondWithGenericException(PLEASE_CHECK_THE_PARAMS)
        }
    }

}
