package com.mak.knote.backend.di.repository

import com.mak.knote.backend.base.auth.JwtConfig
import com.mak.knote.backend.di.ConfigLocator
import com.mak.knote.backend.di.service.ServiceLocator
import com.mak.knote.backend.feature.auth.repository.AuthRepository
import com.mak.knote.backend.feature.auth.repository.IAuthRepository
import com.mak.knote.backend.feature.notes.repository.INotesRepository
import com.mak.knote.backend.feature.notes.repository.NotesRepository
import com.mak.knote.backend.feature.notes.service.INotesApiService
import com.mak.knote.backend.feature.user.service.IUserApiService

internal object RepositoryLocator {
    fun provideAuthRepository(service: IUserApiService): IAuthRepository {
        return AuthRepository(
            service,
            JwtConfig.instance,
            ConfigLocator.provideExceptionHandler()
        )
    }

    fun provideRepositoryProvider(): IRepositoryProvider {
        return RepositoryProvider(ServiceLocator.provideServiceProvider())
    }

    fun provideNotesRepository(service: INotesApiService): INotesRepository {
        return NotesRepository(
            service,
            ConfigLocator.provideExceptionHandler()
        )
    }

}
