package com.mak.knote.backend

import com.mak.knote.backend.base.configureStatusPages
import com.mak.knote.backend.di.ConfigLocator
import com.mak.knote.backend.plugins.configureRouting
import com.mak.knote.backend.plugins.configureSecurity
import com.mak.knote.backend.plugins.configureSerialization
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    ConfigLocator.provideJwtConfig()
    configureSerialization()
    configureSecurity()
    configureRouting()
    configureStatusPages()
}
