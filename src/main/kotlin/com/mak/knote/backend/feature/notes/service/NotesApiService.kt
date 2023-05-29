package com.mak.knote.backend.feature.notes.service

import com.mak.knote.backend.feature.notes.Note
import org.litote.kmongo.and
import org.litote.kmongo.ascending
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.eq
import org.litote.kmongo.exclude
import org.litote.kmongo.fields
import org.litote.kmongo.limit
import org.litote.kmongo.project
import org.litote.kmongo.skip
import org.litote.kmongo.sort

class NotesApiService(
    private val noteCollection: CoroutineCollection<Note>
) : INotesApiService {

    private companion object {
        const val ONE = 1
    }

    override suspend fun getNotesForUser(userId: String, page: Int, limit: Int): Pair<List<Note>, Int> {
        val skips = page.minus(ONE) * limit

        val fields = fields(exclude(Note::isDeleted))

        val noteCondition =
            and(Note::createdBy eq userId/*, Note::isDeleted eq false*/)  // TODO send deleted notes too??
        return Pair(
            noteCollection.aggregate<Note>(
                skip(skips),
                limit(limit),
                project(fields),
                sort(ascending(Note::createdAt)),
                noteCondition
            ).toList(),
            noteCollection.countDocuments(
                noteCondition
            ).toInt()
        )
    }
}