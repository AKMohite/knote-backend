package com.mak.knote.backend.common.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDTO(
    val token: String? = null
) {
    fun validateRequest(): String? {
        return if (token.isNullOrBlank())
            "Bad request. Need required fields."
        else
            null
    }
}
