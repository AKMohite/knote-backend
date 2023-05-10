package com.mak.knote.backend.di.domain

import com.mak.knote.backend.feature.auth.LoginUseCase

internal class DomainProvider : IDomainProvider {
    override fun provideLoginUserUseCase(): LoginUseCase {
        return DomainLocator.provideLoginUserUseCase()
    }
}