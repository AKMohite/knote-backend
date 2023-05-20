package com.mak.knote.backend.di.domain

import com.mak.knote.backend.di.repository.RepositoryLocator
import com.mak.knote.backend.feature.auth.domain.LoginUseCase
import com.mak.knote.backend.feature.auth.domain.SignupUseCase
import com.mak.knote.backend.feature.auth.repository.IAuthRepository

internal object DomainLocator {
    fun provideLoginUserUseCase(repository: IAuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    fun provideSignupUserUseCase(repository: IAuthRepository): SignupUseCase {
        return SignupUseCase(repository)
    }

    fun provideDomainProvider(): IDomainProvider {
        return DomainProvider(RepositoryLocator.provideRepositoryProvider())
    }


}
