package com.mak.knote.backend.base

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import kotlinx.serialization.Serializable

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(
                HttpStatusCode.Unauthorized,
                ExceptionResponse(HttpStatusCode.Unauthorized.value, cause.message.toString())
            )
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(
                HttpStatusCode.Forbidden,
                ExceptionResponse(HttpStatusCode.Forbidden.value, cause.message.toString())
            )
        }
        exception<BadRequestException> { call, cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                ExceptionResponse(HttpStatusCode.BadRequest.value, cause.message.toString())
            )
        }
        exception<NotFoundException> { call, cause ->
            call.respond(
                HttpStatusCode.NotFound,
                ExceptionResponse(HttpStatusCode.NotFound.value, cause.message.toString())
            )
        }
        exception<ConflictException> { call, cause ->
            call.respond(
                HttpStatusCode.Conflict,
                ExceptionResponse(HttpStatusCode.Conflict.value, cause.message.toString())
            )
        }
        exception<SomethingWentWrongException> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ExceptionResponse(HttpStatusCode.ExpectationFailed.value, cause.message.toString())
            )
        }

        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ExceptionResponse(HttpStatusCode.InternalServerError.value, cause.message.toString())
            )
        }
    }
}

class AuthenticationException(message: String?) : NoteException(message)

class ConflictException(message: String?) : NoteException(message)

class AuthorizationException(message: String?) : NoteException(message)

class BadRequestException(message: String?) : NoteException(message)

class NotFoundException(message: String?) : NoteException(message)

class SomethingWentWrongException(message: String?) : NoteException(message)

abstract class NoteException(message: String?) : Exception(message)

@Serializable
data class ExceptionResponse(val code: Int, val message: String? = null)
