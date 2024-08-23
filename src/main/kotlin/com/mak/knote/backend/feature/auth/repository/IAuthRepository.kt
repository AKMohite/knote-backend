package com.mak.knote.backend.feature.auth.repository

import com.mak.knote.backend.common.data.dto.request.LoginDTO
import com.mak.knote.backend.common.data.dto.request.RefreshTokenDTO
import com.mak.knote.backend.common.data.dto.request.RegisterDTO
import com.mak.knote.backend.common.data.dto.response.TokenDTO

internal interface IAuthRepository {

    suspend fun register(registerRequest: RegisterDTO): TokenDTO
    suspend fun login(loginRequest: LoginDTO): TokenDTO
    suspend fun refreshToken(request: RefreshTokenDTO): TokenDTO
}