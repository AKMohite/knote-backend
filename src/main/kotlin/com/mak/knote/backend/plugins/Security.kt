package com.mak.knote.backend.plugins

import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.mak.knote.backend.base.auth.UserIdPrincipalForUser
import com.mak.knote.backend.base.http.IExceptionHandler
import com.mak.knote.backend.common.data.provider.ITokenProvider
import com.mak.knote.backend.util.KnoteConstants
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.jwt
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {

    val tokenProvider by inject<ITokenProvider>()
    val exceptionHandler by inject<IExceptionHandler>()
    val jwtVerifier = tokenProvider.verifier

    authentication {
        jwt(KnoteConstants.APP_NAME) {
            verifier(jwtVerifier)

            challenge { _, _ ->
                // get custom error message if error exists
                val header = call.request.headers["Authorization"]
                if (header.isNullOrBlank()) // Access token not found
                    throw exceptionHandler.respondWithAuthenticateException("Authentication failed")
                try {
                    if ((!header.contains("Bearer", true))) throw JWTDecodeException("")
                    val jwt = header.replace("Bearer ", "")
                    jwtVerifier.verify(jwt)
                } catch (e: TokenExpiredException) {
//                    Access token expired
                    throw exceptionHandler.respondWithAuthenticateException("Authentication failed")
                } catch (e: SignatureVerificationException) {
//                    Failed to parse Access token
                    throw exceptionHandler.respondWithAuthenticateException("Authentication failed")
                } catch (e: JWTDecodeException) {
//                    Failed to parse Access token
                    throw exceptionHandler.respondWithAuthenticateException("Authentication failed")
                }
            }

            validate { credential ->
                credential.payload.getClaim("userId").asString()?.let { userId ->
                    // do database query to find Principal subclass
//                    val user = userDao.findByID(userId)
//                    user?.let {
//                        UserPrincipal(it)
//                    }
                    UserIdPrincipalForUser(userId)
                }
                    ?: throw exceptionHandler.respondWithAuthenticateException("Authentication failed") // user id not found
            }
        }
    }
}
