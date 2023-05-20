package com.mak.knote.backend.di

import com.mak.knote.backend.base.auth.JwtConfig
import com.mak.knote.backend.base.http.ExceptionHandler
import com.mak.knote.backend.base.http.IExceptionHandler

internal object ConfigLocator {

    fun provideJwtConfig() {
        return JwtConfig.initialize("ktor-backend-knote-not-so-secret")
    }

    fun provideExceptionHandler(): IExceptionHandler {
        return ExceptionHandler()
    }

}
