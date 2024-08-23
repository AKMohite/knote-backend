package com.mak.knote.backend.common.data.provider

import com.auth0.jwt.JWTVerifier
import com.mak.knote.backend.feature.user.User
import java.util.*

internal typealias JWTTokens = Pair<String, String>

internal interface ITokenProvider {
    val verifier: JWTVerifier
    fun createTokens(user: User): JWTTokens
    fun verifyTokenType(token: String): String
    fun verifyToken(token: String): String?
    fun getTokenExpiration(token: String): Date
}