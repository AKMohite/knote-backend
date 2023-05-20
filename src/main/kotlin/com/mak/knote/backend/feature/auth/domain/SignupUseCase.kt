package com.mak.knote.backend.feature.auth.domain

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.feature.auth.LoginRequest
import com.mak.knote.backend.feature.auth.repository.IAuthRepository

internal class SignupUseCase(
    private val authRepository: IAuthRepository
) : BaseUseCase<LoginRequest, String> {
    override suspend operator fun invoke(request: LoginRequest): BaseResponse<String> {
        return authRepository.signup(request)
    }
}
