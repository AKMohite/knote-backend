package com.mak.knote.backend.feature.notes.service

import com.mak.knote.backend.feature.notes.NoteEntity
import com.mak.knote.backend.util.KnoteConstants
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext


class NotesApiService(
    db: MongoDatabase
) : INotesApiService {

    private val collection = db.getCollection<NoteEntity>(KnoteConstants.NOTES_TABLE)

    private companion object {
        const val ONE = 1
    }

    override suspend fun insertNote(userId: String, note: NoteEntity): Boolean = withContext(Dispatchers.IO) {
//        collection.insertOne(note).insertedId.asObjectId().value
        return@withContext collection.insertOne(note).wasAcknowledged()
    }

    override suspend fun getNotesForUser(userId: String, page: Int, limit: Int): Pair<List<NoteEntity>, Int> =
        withContext(Dispatchers.IO) {
        val skips = page.minus(ONE) * limit

            val filter = Filters.and(Filters.eq(NoteEntity::createdBy.name, userId))
            val notes = collection.find(filter).toList()
            return@withContext Pair(notes, 20) // TODO count is not computed
    }

    override suspend fun getNoteById(noteId: String?): NoteEntity? = withContext(Dispatchers.IO) {
        return@withContext noteId?.let { id ->
            collection.find(
                Filters.and(Filters.eq(NoteEntity::_id.name, id))
            )
        }?.firstOrNull()
    }

    override suspend fun updateNote(noteToUpdate: NoteEntity): Boolean = withContext(Dispatchers.IO) {
        val filter = Filters.eq(NoteEntity::_id.name, noteToUpdate._id)
        return@withContext collection.replaceOne(filter, noteToUpdate).wasAcknowledged()
    }

    override suspend fun deleteNote(noteId: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext collection.deleteOne(Filters.eq(NoteEntity::_id.name, noteId)).wasAcknowledged()
    }

}