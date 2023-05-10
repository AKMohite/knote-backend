package com.mak.knote.backend.feature.user.service

import com.mak.knote.backend.feature.user.User

internal interface IUserApiService {
    abstract fun findUserByEmail(email: String): User?

}
