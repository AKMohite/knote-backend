package com.mak.knote.backend.feature.auth.repository

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.SuccessResponse
import com.mak.knote.backend.base.auth.JwtConfig
import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.feature.auth.LoginRequest
import com.mak.knote.backend.feature.user.User
import com.mak.knote.backend.feature.user.service.IUserApiService
import com.mak.knote.backend.util.checkHashForPassword
import io.ktor.http.HttpStatusCode

internal class AuthRepository(
    private val userApiService: IUserApiService,
    private val jwtConfig: JwtConfig,
    private val exceptionHandler: IExceptionHandler
) : IAuthRepository {

    /**
     * All static constant containing the Error code for [IExceptionHandler]
     */
    private companion object {
        private const val USER_ALREADY_EXIST_MESSAGE = "User already exists, Please login"
        private const val EITHER_USERNAME_PASSWORD_INCORRECT = "Either username or password is incorrect"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val USER_DONT_EXIST_MESSAGE = "User doesn't exists, Please register"
        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again"
    }

    override suspend fun loginUser(request: LoginRequest): BaseResponse<Any> {
        return if (checkIfUsersExist(request.email)) {
            val user: User? = userApiService.findUserByEmail(request.email)
            if (user != null) {
                val hashedPasswordIsSame = user.passwordHash?.let { checkHashForPassword(request.password, it) }
                when (hashedPasswordIsSame) {
                    true -> SuccessResponse(
                        data = jwtConfig.makeAccessToken(user.id),
                        statusCode = HttpStatusCode.OK
                    )

                    else -> throw exceptionHandler.respondWithUnauthorizedException(EITHER_USERNAME_PASSWORD_INCORRECT)
                }
            } else throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(USER_DONT_EXIST_MESSAGE)
        }
    }


    private suspend fun checkIfUsersExist(email: String): Boolean {
        return userApiService.findUserByEmail(email) != null
    }
}
