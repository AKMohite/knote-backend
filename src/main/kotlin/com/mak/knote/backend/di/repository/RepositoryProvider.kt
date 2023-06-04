package com.mak.knote.backend.di.repository

import com.mak.knote.backend.di.service.IServiceProvider
import com.mak.knote.backend.feature.auth.repository.IAuthRepository
import com.mak.knote.backend.feature.notes.repository.INotesRepository

internal class RepositoryProvider(
    private val serviceProvider: IServiceProvider
) : IRepositoryProvider {
    override fun provideAuthRepository(): IAuthRepository {
        return RepositoryLocator.provideAuthRepository(
            serviceProvider.provideUserApiService()
        )
    }

    override fun provideNoteRepository(): INotesRepository {
        return RepositoryLocator.provideNotesRepository(
            serviceProvider.provideNoteApiService()
        )
    }
}