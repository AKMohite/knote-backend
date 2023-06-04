package com.mak.knote.backend.feature.notes.repository

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.feature.notes.NoteDTO

internal interface INotesRepository {
    suspend fun getNotesForUser(userId: String, page: Int, limit: Int): BaseResponse<List<NoteDTO>>
    suspend fun createNote(userId: String, note: NoteDTO): BaseResponse<NoteDTO>
    suspend fun updateNote(userId: String, noteId: String, noteDTO: NoteDTO): BaseResponse<NoteDTO>
    suspend fun deleteNote(userId: String, noteId: String): BaseResponse<NoteDTO>

}
