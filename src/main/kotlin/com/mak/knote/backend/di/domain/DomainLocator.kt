package com.mak.knote.backend.di.domain

import com.mak.knote.backend.feature.auth.LoginUseCase

internal object DomainLocator {
    fun provideLoginUserUseCase(): LoginUseCase {
        return LoginUseCase()
    }

    fun provideDomainProvider(): IDomainProvider {
        return DomainProvider()
    }

}
