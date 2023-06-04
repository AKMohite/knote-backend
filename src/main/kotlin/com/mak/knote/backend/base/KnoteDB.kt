package com.mak.knote.backend.base

import com.mak.knote.backend.feature.notes.Note
import com.mak.knote.backend.feature.user.User
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

internal class KnoteDB(
    private val clientName: String
) : IKnoteDB {
    override val initializeName: String
        get() = clientName
    override val mongoClient: CoroutineClient
        get() = KMongo.createClient().coroutine

    override val database: CoroutineDatabase
        get() = mongoClient.getDatabase(initializeName)

    override val userCollection: CoroutineCollection<User>
        get() = database.getCollection()

    override val noteCollection: CoroutineCollection<Note>
        get() = database.getCollection()
}