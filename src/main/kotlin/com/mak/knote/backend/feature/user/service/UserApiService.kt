package com.mak.knote.backend.feature.user.service

import com.mak.knote.backend.feature.user.User

internal class UserApiService : IUserApiService {
    override suspend fun findUserByEmail(email: String): User? {
        return null
    }
}