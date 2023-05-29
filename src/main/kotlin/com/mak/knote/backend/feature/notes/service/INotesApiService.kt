package com.mak.knote.backend.feature.notes.service

import com.mak.knote.backend.feature.notes.Note

interface INotesApiService {
    suspend fun getNotesForUser(userId: String, page: Int, limit: Int): Pair<List<Note>, Int>
}
