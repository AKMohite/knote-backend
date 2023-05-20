package com.mak.knote.backend.base.auth

import io.ktor.server.auth.Principal

internal data class UserIdPrincipalForUser(
    val userId: String
) : Principal
