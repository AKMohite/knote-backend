package com.mak.knote.backend.feature.notes.service

import com.mak.knote.backend.feature.notes.NoteEntity

interface INotesApiService {
    suspend fun getNotesForUser(userId: String, page: Int, limit: Int): Pair<List<NoteEntity>, Int>
    suspend fun insertNote(userId: String, note: NoteEntity): Boolean
    suspend fun getNoteById(noteId: String?): NoteEntity?
    suspend fun updateNote(noteToUpdate: NoteEntity): Boolean
    suspend fun deleteNote(noteId: String): Boolean
}
