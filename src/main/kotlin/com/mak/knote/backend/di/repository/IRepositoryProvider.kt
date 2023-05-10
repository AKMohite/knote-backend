package com.mak.knote.backend.di.repository

import com.mak.knote.backend.feature.auth.repository.IAuthRepository

internal interface IRepositoryProvider {
    fun provideAuthRepository(): IAuthRepository
}
