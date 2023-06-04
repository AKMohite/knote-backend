package com.mak.knote.backend.plugins

import com.mak.knote.backend.base.auth.JwtConfig
import com.mak.knote.backend.base.auth.UserIdPrincipalForUser
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity() {
    
    authentication {
            jwt {
//                val jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
//                realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
                verifier(
                    JwtConfig.instance.verifier
                )
                validate { credential ->
//                    if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
                    val claim = credential.payload.getClaim(JwtConfig.ClAIM).asString()
                    if (claim != null) {
                        UserIdPrincipalForUser(claim)
                    } else {
                        null
                    }
                }
            }
        }
}
