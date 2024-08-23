package com.mak.knote.backend.feature.user.service

import com.mak.knote.backend.feature.user.UserEntity
import com.mak.knote.backend.util.KnoteConstants
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

internal class UserApiService(
    db: MongoDatabase
) : IUserApiService {

    private val collection = db.getCollection<UserEntity>(KnoteConstants.USERS_TABLE)

    override suspend fun getUserBy(email: String): UserEntity? = withContext(Dispatchers.IO) {
        return@withContext collection.find(Filters.eq(UserEntity::email.name, email)).firstOrNull()
    }

    override suspend fun store(entity: UserEntity): UserEntity? = withContext(Dispatchers.IO) {
        val result = collection.insertOne(entity)
        if (result.wasAcknowledged())
            entity
        else
            null
    }

    override suspend fun getUserById(userId: String): UserEntity? = withContext(Dispatchers.IO) {
        return@withContext collection.find(Filters.eq(UserEntity::_id.name, userId)).firstOrNull()
    }
}