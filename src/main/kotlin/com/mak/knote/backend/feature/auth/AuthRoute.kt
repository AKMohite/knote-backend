package com.mak.knote.backend.feature.auth

import com.mak.knote.backend.base.BadRequestException
import com.mak.knote.backend.di.domain.IDomainProvider
import com.mak.knote.backend.util.KnoteConstants.LOGIN_ROUTE
import com.mak.knote.backend.util.KnoteConstants.SIGNUP_ROUTE
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

internal fun Routing.authRoutes(domainProvider: IDomainProvider) {
    post(LOGIN_ROUTE) {
        val request = kotlin.runCatching { call.receiveNullable<LoginRequest>() }.getOrNull() ?: kotlin.run {
            throw BadRequestException("Bad request")
        }
        val response = domainProvider.provideLoginUserUseCase().invoke(request)
        call.respond(response.statusCode, response)
    }

    post(SIGNUP_ROUTE) {
        val request = kotlin.runCatching { call.receiveNullable<LoginRequest>() }.getOrNull() ?: kotlin.run {
            throw BadRequestException("Bad request")
        }
        val response = domainProvider.provideSignupUserUseCase().invoke(request)
        call.respond(response.statusCode, response)
    }
}

data class LoginRequest(
    val email: String,
    val password: String
)
