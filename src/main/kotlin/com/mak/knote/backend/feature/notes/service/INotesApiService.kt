package com.mak.knote.backend.feature.notes.service

import com.mak.knote.backend.feature.notes.Note

interface INotesApiService {
    suspend fun getNotesForUser(userId: String, page: Int, limit: Int): Pair<List<Note>, Int>
    suspend fun insertNote(userId: String, note: Note): Boolean
    suspend fun getNoteById(noteId: String?): Note?
    suspend fun updateNote(noteToUpdate: Note): Boolean
    suspend fun deleteNote(noteId: String): Boolean
}
