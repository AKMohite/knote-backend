package com.mak.knote.backend.di.repository

import com.mak.knote.backend.di.service.IServiceProvider
import com.mak.knote.backend.feature.auth.repository.IAuthRepository

internal class RepositoryProvider(
    private val serviceProvider: IServiceProvider
) : IRepositoryProvider {
    override fun provideAuthRepository(): IAuthRepository {
        return RepositoryLocator.provideAuthRepository(
            serviceProvider.provideUserApiService()
        )
    }
}