package com.mak.knote.backend.feature.auth.repository

import com.mak.knote.backend.base.auth.IPasswordEncryptor
import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.common.data.dto.request.LoginDTO
import com.mak.knote.backend.common.data.dto.request.RefreshTokenDTO
import com.mak.knote.backend.common.data.dto.request.RegisterDTO
import com.mak.knote.backend.common.data.dto.response.TokenDTO
import com.mak.knote.backend.common.data.provider.ITokenProvider
import com.mak.knote.backend.feature.user.UserMapper
import com.mak.knote.backend.feature.user.service.IUserApiService
import com.mak.knote.backend.util.KnoteConstants
import com.mak.knote.backend.util.internalRun
import com.mak.knote.backend.util.requireNonNullable

internal class AuthRepository(
    private val exceptionHandler: IExceptionHandler,
    private val tokenProvider: ITokenProvider,
    private val userDAO: IUserApiService,
    private val userMapper: UserMapper,
    private val encryptor: IPasswordEncryptor
) : IAuthRepository {
    override suspend fun register(registerRequest: RegisterDTO): TokenDTO {
        return internalRun {
            registerRequest.validateRequest()?.let { errorMessage ->
                throw exceptionHandler.respondWithBadRequestException(errorMessage)
            }
            val entity = userDAO.getUserBy(registerRequest.email.requireNonNullable())
            if (entity != null) // email found in DB
                throw exceptionHandler.respondWithAlreadyExistException("Account already registered. Please login.")
//            TODO send email verification email to confirm account registration
            val password = encryptor.generateHash(registerRequest.password.requireNonNullable())
            val newEntity = userMapper.jsonToEntity(registerRequest).copy(password = password)
            val storedUser = userDAO.store(newEntity) ?: // unable to store in DB
            throw exceptionHandler.respondWithSomethingWentWrongException("Cannot create account. Please try again later.")
            val user = userMapper.entityToModel(storedUser)
            val (accessToken, refreshToken) = tokenProvider.createTokens(user)
            TokenDTO(
                accessToken,
                refreshToken
            )
        }
    }

    override suspend fun login(loginRequest: LoginDTO): TokenDTO = internalRun {
        loginRequest.validateRequest()?.let { errorMessage ->
            throw exceptionHandler.respondWithBadRequestException(errorMessage)
        }
        val entity = userDAO.getUserBy(loginRequest.email.requireNonNullable()) ?: // account not registered
        throw exceptionHandler.respondWithNotFoundException("Account not registered. Please register your account.")
        if (!encryptor.validatePassword(
                entity.password,
                loginRequest.password.requireNonNullable()
            )
        ) // password does not match
            throw exceptionHandler.respondWithUnauthorizedException("Invalid credentials. Please check and try again.")
        val user = userMapper.entityToModel(entity)
        val (accessToken, refreshToken) = tokenProvider.createTokens(user)
        TokenDTO(
            accessToken,
            refreshToken
        )
    }

    override suspend fun refreshToken(request: RefreshTokenDTO): TokenDTO = internalRun {
        request.validateRequest()?.let { errorMessage ->
            throw exceptionHandler.respondWithBadRequestException(errorMessage)
        }
        val refreshToken = request.token.requireNonNullable()
        val userId = tokenProvider.verifyToken(refreshToken) ?: // token is not proper
        throw exceptionHandler.respondWithUnauthorizedException("Authentication failed. Please logout")
        if (tokenProvider.verifyTokenType(refreshToken) != KnoteConstants.REFRESH_TOKEN_TYPE) // access type does not match
            throw exceptionHandler.respondWithUnauthorizedException("Authentication failed. Please logout")
        val entity = userDAO.getUserById(userId) ?: // user id from token is not found in DB
        throw exceptionHandler.respondWithUnauthorizedException("Authentication failed. Please logout")
        val user = userMapper.entityToModel(entity)
        val (accessToken, newRefreshToken) = tokenProvider.createTokens(user)
        TokenDTO(
            accessToken,
            newRefreshToken
        )
    }
}
