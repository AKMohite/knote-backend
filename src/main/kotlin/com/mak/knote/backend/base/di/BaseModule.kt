package com.mak.knote.backend.base.di

import com.mak.knote.backend.base.auth.IPasswordEncryptor
import com.mak.knote.backend.base.auth.PasswordEncryptor
import com.mak.knote.backend.base.http.ExceptionHandler
import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.common.data.provider.ITokenProvider
import com.mak.knote.backend.common.data.provider.JWTTokenProvider
import com.mak.knote.backend.util.KnoteConstants
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import org.koin.dsl.module

object BaseModule {
    val koinBeans = module {
        single<MongoDatabase> {
            val uri = "mongodb://localhost:27017"
            val mongoClient = MongoClient.create()
            mongoClient.getDatabase(KnoteConstants.DATABASE_NAME)
        }
        single<IExceptionHandler> { ExceptionHandler() }
        single<ITokenProvider> { JWTTokenProvider() }
        single<IPasswordEncryptor> { PasswordEncryptor() }
    }
}