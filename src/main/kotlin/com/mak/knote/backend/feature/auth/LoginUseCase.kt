package com.mak.knote.backend.feature.auth

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.base.SuccessResponse
import io.ktor.http.HttpStatusCode

internal class LoginUseCase: BaseUseCase<LoginRequest, Any> {
    override suspend operator fun invoke(request: LoginRequest): BaseResponse<Any> {
        return SuccessResponse(HttpStatusCode.Created, "done")
    }
}
