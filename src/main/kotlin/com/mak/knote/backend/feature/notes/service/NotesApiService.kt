package com.mak.knote.backend.feature.notes.service

import com.mak.knote.backend.feature.notes.NoteEntity
import com.mak.knote.backend.util.KnoteConstants
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NotesApiService(
    db: MongoDatabase
) : INotesApiService {

    private val collection = db.getCollection<NoteEntity>(KnoteConstants.NOTES_TABLE)

    private companion object {
        const val ONE = 1
    }

    override suspend fun insertNote(userId: String, note: NoteEntity): Boolean = withContext(Dispatchers.IO) {
        return@withContext collection.insertOne(note).wasAcknowledged()
    }

    override suspend fun getNotesForUser(userId: String, page: Int, limit: Int): Pair<List<NoteEntity>, Int> =
        withContext(Dispatchers.IO) {
        val skips = page.minus(ONE) * limit

            val filter = Filters.and(Filters.eq(NoteEntity::createdBy.name, userId))
            val notes = collection.find(filter)
//        val count = collection.countDocuments(filter).toInt()
//        return Pair(
//            collection.aggregate<NoteEntity>(
//                skip(skips),
//                limit(limit),
//                project(fields),
//                sort(ascending(NoteEntity::createdAt)),
//                match(NoteEntity::createdBy eq userId)
//            ).toList(),
//            count
//        )
    }

    override suspend fun getNoteById(noteId: String?): NoteEntity? = withContext(Dispatchers.IO) {
        return noteId?.let { collection.find(Filters.and(Filters.eq(NoteEntity::id.name, it))) }
    }

    override suspend fun updateNote(noteToUpdate: NoteEntity): Boolean = withContext(Dispatchers.IO) {
        return@withContext collection.updateOneById(noteToUpdate.id, noteToUpdate).wasAcknowledged()
    }

    override suspend fun deleteNote(noteId: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext collection.deleteOneById(noteId).wasAcknowledged()
    }

}