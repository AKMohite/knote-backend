package com.mak.knote.backend.feature.user.service

import com.mak.knote.backend.feature.user.User
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

internal class UserApiService(
    private val userCollection: CoroutineCollection<User>
) : IUserApiService {

    override suspend fun insertUser(user: User): Boolean {
        return userCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun findUserByEmail(email: String): User? {
        return userCollection.findOne(User::email eq email)
    }
}