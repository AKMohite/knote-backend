package com.mak.knote.backend.feature.user.service

import com.mak.knote.backend.feature.user.UserEntity

internal interface IUserApiService {
    suspend fun getUserBy(email: String): UserEntity?
    suspend fun store(entity: UserEntity): UserEntity?
    suspend fun getUserById(userId: String): UserEntity?

}
