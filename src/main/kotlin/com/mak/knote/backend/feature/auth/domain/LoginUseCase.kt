package com.mak.knote.backend.feature.auth.domain

import com.mak.knote.backend.base.BadRequestException
import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.feature.auth.LoginRequest
import com.mak.knote.backend.feature.auth.repository.IAuthRepository
import com.mak.knote.backend.util.isValidEmail

internal class LoginUseCase(
    private val authRepository: IAuthRepository
) : BaseUseCase<LoginRequest, String> {
    override suspend operator fun invoke(request: LoginRequest): BaseResponse<String> {
        if (request.email.isBlank() || request.password.isBlank() || request.password.length < 7) {
            throw BadRequestException("Bad request")
        }
        if (!isValidEmail(request.email)) {
            throw BadRequestException("Not valid email address")
        }
        return authRepository.loginUser(request)
    }
}
