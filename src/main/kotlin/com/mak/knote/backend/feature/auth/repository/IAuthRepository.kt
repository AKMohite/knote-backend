package com.mak.knote.backend.feature.auth.repository

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.feature.auth.LoginRequest

internal interface IAuthRepository {

    suspend fun signup(authRequest: LoginRequest): BaseResponse<String>
    suspend fun loginUser(request: LoginRequest): BaseResponse<String>
}