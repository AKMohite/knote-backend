package com.mak.knote.backend.di.domain

import com.mak.knote.backend.feature.auth.domain.LoginUseCase
import com.mak.knote.backend.feature.auth.domain.SignupUseCase

internal interface IDomainProvider {
    fun provideLoginUserUseCase(): LoginUseCase
    fun provideSignupUserUseCase(): SignupUseCase
}
