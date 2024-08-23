package com.mak.knote.backend.di.repository

import com.mak.knote.backend.feature.auth.repository.AuthRepository
import com.mak.knote.backend.feature.auth.repository.IAuthRepository
import com.mak.knote.backend.feature.user.UserMapper
import com.mak.knote.backend.feature.user.repository.IUserRepository
import com.mak.knote.backend.feature.user.repository.UserRepository
import com.mak.knote.backend.feature.user.service.IUserApiService
import com.mak.knote.backend.feature.user.service.UserApiService
import org.koin.dsl.module

object RepositoryModule {

    val koinBeans = module {
        single<IUserApiService> { UserApiService(db = get()) }
        single<IAuthRepository> {
            AuthRepository(
                exceptionHandler = get(),
                tokenProvider = get(),
                userDAO = get(),
                userMapper = UserMapper,
                encryptor = get()
            )
        }
        single<IUserRepository> {
            UserRepository(
                userDAO = get(),
                exceptionHandler = get(),
                mapper = UserMapper
            )
        }
    }
}

