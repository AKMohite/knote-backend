package com.mak.knote.backend.feature.user.repository

import com.mak.knote.backend.common.data.dto.response.UserDTO

internal interface IUserRepository {
    suspend fun getInfoFor(userID: String): UserDTO

}
