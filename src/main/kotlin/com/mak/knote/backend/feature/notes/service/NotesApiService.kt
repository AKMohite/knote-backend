package com.mak.knote.backend.feature.notes.service

/*class NotesApiService(
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

    override suspend fun getNoteById(noteId: String?): Note? {
        return noteId?.let { noteCollection.findOneById(it) }
    }

    override suspend fun updateNote(noteToUpdate: Note): Boolean {
        return noteCollection.updateOneById(noteToUpdate.id, noteToUpdate).wasAcknowledged()
    }

    override suspend fun deleteNote(noteId: String): Boolean {
        return noteCollection.deleteOneById(noteId).wasAcknowledged()
    }

}*/