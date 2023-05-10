package com.mak.knote.backend.di.service

import com.mak.knote.backend.feature.user.service.IUserApiService
import com.mak.knote.backend.feature.user.service.UserApiService

internal object ServiceLocator {
    fun provideUserApiService(): IUserApiService {
        return UserApiService()
    }

    fun provideServiceProvider(): IServiceProvider {
        return ServiceProvider()
    }

}
