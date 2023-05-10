package com.mak.knote.backend.di.domain

import com.mak.knote.backend.feature.auth.LoginUseCase

internal interface IDomainProvider {
    fun provideLoginUserUseCase(): LoginUseCase
}
