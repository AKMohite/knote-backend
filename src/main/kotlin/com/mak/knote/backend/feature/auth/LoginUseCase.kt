package com.mak.knote.backend.feature.auth

import com.mak.knote.backend.base.AuthenticationException

class LoginUseCase {
    suspend operator fun invoke(request: LoginRequest) {
        throw AuthenticationException("what are you trying to do?")
    }
}
