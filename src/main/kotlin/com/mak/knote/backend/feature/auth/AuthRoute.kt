package com.mak.knote.backend.feature.auth

import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.common.data.dto.request.LoginDTO
import com.mak.knote.backend.common.data.dto.request.RefreshTokenDTO
import com.mak.knote.backend.common.data.dto.request.RegisterDTO
import com.mak.knote.backend.feature.auth.repository.IAuthRepository
import com.mak.knote.backend.util.KnoteConstants
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

internal fun Routing.authRoutes() {
    val authRepo by inject<IAuthRepository>()
    val exceptionHandler by inject<IExceptionHandler>()

    post(KnoteConstants.SIGNUP_ROUTE) {
        val registerRequest =
            call.receiveNullable<RegisterDTO>() ?: throw exceptionHandler.respondWithBadRequestException("Bad request")
        val response = authRepo.register(registerRequest)
        call.respond(HttpStatusCode.Created, response)
    }

    post(KnoteConstants.LOGIN_ROUTE) {
        val loginRequest =
            call.receiveNullable<LoginDTO>() ?: throw exceptionHandler.respondWithBadRequestException("Bad request")
        val response = authRepo.login(loginRequest)
        call.respond(HttpStatusCode.OK, response)
    }

    post(KnoteConstants.REFRESH_TOKEN_ROUTE) {
        val request =
            call.receiveNullable<RefreshTokenDTO>()
                ?: throw exceptionHandler.respondWithBadRequestException("Bad request")
        val response = authRepo.refreshToken(request)
        call.respond(HttpStatusCode.OK, response)
    }
}