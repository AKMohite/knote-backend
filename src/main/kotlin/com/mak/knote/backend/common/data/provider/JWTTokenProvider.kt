package com.mak.knote.backend.common.data.provider

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.mak.knote.backend.feature.user.User
import com.mak.knote.backend.util.KnoteConstants
import java.util.*

internal class JWTTokenProvider : ITokenProvider {

    private val secret = "blog-secret-for-token"
    private val issuer = "blog-issuer-for-token"
    private val jwtAudience = "blog-jwt"
    private val validityInMs: Long = 1200000L // 20 Minutes
    private val refreshValidityInMs: Long = 3600000L * 24L * 30L // 30 days
    private val algorithm = Algorithm.HMAC512(secret)
    override val verifier: JWTVerifier
        get() = JWT
            .require(algorithm)
            .withIssuer(issuer)
            .withAudience(jwtAudience)
            .build()

    override fun verifyToken(token: String): String? {
        return verifier.verify(token).claims["userId"]?.asString()
    }

    override fun getTokenExpiration(token: String): Date {
        return verifier.verify(token).expiresAt
    }

    /**
     * Produce token and refresh token for this combination of User and Account
     */
    override fun createTokens(user: User) = Pair(
        createAccessToken(user, getTokenExpiration()),
        createRefreshToken(user, getTokenExpiration(refreshValidityInMs))
    )

    override fun verifyTokenType(token: String): String {
        return verifier.verify(token).claims["tokenType"]!!.asString()
    }

    private fun createAccessToken(user: User, expiration: Date) =
        createToken(user, expiration, KnoteConstants.ACCESS_TOKEN_TYPE)

    private fun createRefreshToken(user: User, expiration: Date) =
        createToken(user, expiration, KnoteConstants.REFRESH_TOKEN_TYPE)

    private fun createToken(user: User, expiration: Date, tokenType: String): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("userId", user.id)
        .withClaim("tokenType", tokenType)
        .withExpiresAt(expiration)
        .withAudience(jwtAudience)
        .sign(algorithm)

    /**
     * Calculate the expiration Date based on current time + the given validity
     */
    private fun getTokenExpiration(validity: Long = validityInMs) = Date(System.currentTimeMillis() + validity)
}