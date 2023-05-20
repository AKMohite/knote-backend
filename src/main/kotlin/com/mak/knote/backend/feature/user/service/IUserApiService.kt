package com.mak.knote.backend.feature.user.service

import com.mak.knote.backend.feature.user.User

internal interface IUserApiService {
    suspend fun findUserByEmail(email: String): User?
    suspend fun insertUser(user: User): Boolean

}
