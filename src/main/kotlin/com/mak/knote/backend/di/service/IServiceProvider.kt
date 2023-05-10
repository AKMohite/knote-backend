package com.mak.knote.backend.di.service

import com.mak.knote.backend.feature.user.service.IUserApiService

internal interface IServiceProvider {
    fun provideUserApiService(): IUserApiService

}
