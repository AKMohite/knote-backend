package com.mak.knote.backend.base

import com.mak.knote.backend.base.di.BaseModule
import com.mak.knote.backend.di.repository.RepositoryModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(
            BaseModule.koinBeans,
            RepositoryModule.koinBeans
        )
    }
}