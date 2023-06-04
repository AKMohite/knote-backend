package com.mak.knote.backend.feature.notes.service

import com.mak.knote.backend.feature.notes.Note
import org.litote.kmongo.ascending
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.eq
import org.litote.kmongo.exclude
import org.litote.kmongo.fields
import org.litote.kmongo.limit
import org.litote.kmongo.match
import org.litote.kmongo.project
import org.litote.kmongo.skip
import org.litote.kmongo.sort
import org.litote.kmongo.util.KMongoUtil

class NotesApiService(
    private val noteCollection: CoroutineCollection<Note>
) : INotesApiService {

    private companion object {
        const val ONE = 1
    }

    override suspend fun insertNote(userId: String, note: Note): Boolean {
        return noteCollection.insertOne(note).wasAcknowledged()
    }

    override suspend fun getNotesForUser(userId: String, page: Int, limit: Int): Pair<List<Note>, Int> {
        val skips = page.minus(ONE) * limit

        val fields = fields(exclude(Note::isDeleted))
        val filter =
            KMongoUtil.toBson("""{ "createdBy": "$userId", "isDeleted": false } """) // TODO send deleted notes too??
        val count = noteCollection.countDocuments(filter).toInt()
        return Pair(
            noteCollection.aggregate<Note>(
                skip(skips),
                limit(limit),
                project(fields),
                sort(ascending(Note::createdAt)),
                match(Note::createdBy eq userId)
            ).toList(),
            count
        )
    }
}