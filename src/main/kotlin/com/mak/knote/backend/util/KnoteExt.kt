package com.mak.knote.backend.util

import com.mak.knote.backend.base.AuthorizationException
import com.mak.knote.backend.base.auth.UserIdPrincipalForUser
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.authentication
import io.ktor.server.request.receive
import io.ktor.util.pipeline.PipelineContext

fun PipelineContext<*, ApplicationCall>.getUserIdFromToken(): String {
    val principal = this.call.authentication.principal<UserIdPrincipalForUser>()
    return principal?.userId ?: throw AuthorizationException("Not authorized")
}

suspend inline fun <reified T : Any> PipelineContext<*, ApplicationCall>.getBodyContent(): T {
    return call.receive()
}
