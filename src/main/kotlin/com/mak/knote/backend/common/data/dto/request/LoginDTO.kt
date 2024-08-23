package com.mak.knote.backend.common.data.dto.request

import com.mak.knote.backend.util.isEmailValid
import kotlinx.serialization.Serializable

@Serializable
class LoginDTO(
    val email: String? = null,
    val password: String? = null
) {
    fun validateRequest(): String? {
        return when {
            (email.isNullOrBlank() || password.isNullOrBlank()) -> "Credentials fields should not be blank"
            (!email.isEmailValid()) -> "Email invalid"
            (password.length !in (8..50)) -> "Password should be of min 8 and max 50 character in length"
            else -> null
        }
    }
}
