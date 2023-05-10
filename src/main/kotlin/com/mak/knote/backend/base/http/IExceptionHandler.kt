package com.mak.knote.backend.base.http

internal interface IExceptionHandler {
    fun respondWithBadRequestException(message: String?): Exception

    fun respondWithUnauthorizedException(message: String?): Exception

    fun respondWithNotFoundException(message: String?): Exception

    fun respondWithAlreadyExistException(message: String?): Exception

    fun respondWithGenericException(message: String?): Exception

    fun respondWithSomethingWentWrongException(message: String = "Something went wrong"): Exception
}
