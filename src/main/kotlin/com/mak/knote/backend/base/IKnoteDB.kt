package com.mak.knote.backend.base

import com.mak.knote.backend.feature.notes.Note
import com.mak.knote.backend.feature.user.User
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

internal interface IKnoteDB {

    val initializeName: String

    val mongoClient: CoroutineClient

    val database: CoroutineDatabase

    val userCollection: CoroutineCollection<User>

    val noteCollection: CoroutineCollection<Note>
}