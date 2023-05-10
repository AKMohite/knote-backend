package com.mak.knote.backend.di.domain

import com.mak.knote.backend.di.repository.IRepositoryProvider
import com.mak.knote.backend.feature.auth.domain.LoginUseCase

internal class DomainProvider(
    private val repositoryProvider: IRepositoryProvider
) : IDomainProvider {
    override fun provideLoginUserUseCase(): LoginUseCase {
        return DomainLocator.provideLoginUserUseCase(repositoryProvider.provideAuthRepository())
    }
}