package com.mak.knote.backend.feature.notes.repository

import com.mak.knote.backend.feature.notes.NoteDTO

internal interface INotesRepository {
    suspend fun getNotesForUser(userId: String, page: Int, limit: Int): List<NoteDTO>
    suspend fun createNote(userId: String, note: NoteDTO): NoteDTO
    suspend fun updateNote(userId: String, noteId: String, noteDTO: NoteDTO): NoteDTO
    suspend fun deleteNote(userId: String, noteId: String): NoteDTO

}
