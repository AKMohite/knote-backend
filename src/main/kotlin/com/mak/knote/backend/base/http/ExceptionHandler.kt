package com.mak.knote.backend.base.http

import com.mak.knote.backend.base.AuthorizationException
import com.mak.knote.backend.base.BadRequestException
import com.mak.knote.backend.base.ConflictException
import com.mak.knote.backend.base.NotFoundException
import com.mak.knote.backend.base.SomethingWentWrongException

class ExceptionHandler : IExceptionHandler {
    override fun respondWithBadRequestException(message: String?): Exception {
        return BadRequestException(message)
    }

    override fun respondWithUnauthorizedException(message: String?): Exception {
        return AuthorizationException(message)
    }

    override fun respondWithNotFoundException(message: String?): Exception {
        return NotFoundException(message)
    }

    override fun respondWithAlreadyExistException(message: String?): Exception {
        return ConflictException(message)
    }

    override fun respondWithGenericException(message: String?): Exception {
        return SomethingWentWrongException(message)
    }

    override fun respondWithSomethingWentWrongException(message: String): Exception {
        return SomethingWentWrongException(message)
    }
}