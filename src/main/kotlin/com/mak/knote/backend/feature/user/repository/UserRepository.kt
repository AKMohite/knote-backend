package com.mak.knote.backend.feature.user.repository

import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.common.data.dto.response.UserDTO
import com.mak.knote.backend.feature.user.UserMapper
import com.mak.knote.backend.feature.user.service.IUserApiService
import com.mak.knote.backend.util.internalRun

internal class UserRepository(
    private val userDAO: IUserApiService,
    private val exceptionHandler: IExceptionHandler,
    private val mapper: UserMapper
) : IUserRepository {

    override suspend fun getInfoFor(userID: String): UserDTO = internalRun {
        val entity = userDAO.getUserById(userID) ?: // No user found for this id
        throw exceptionHandler.respondWithNotFoundException("No data found for this user")
        val user = mapper.entityToJson(entity)
        return@internalRun user
    }

}