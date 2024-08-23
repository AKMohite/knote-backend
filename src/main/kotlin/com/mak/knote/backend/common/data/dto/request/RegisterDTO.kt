package com.mak.knote.backend.common.data.dto.request

import com.mak.knote.backend.util.isAlphaNumeric
import com.mak.knote.backend.util.isEmailValid
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterDTO(
    @SerialName("user_name")
    val userName: String? = null,
    val email: String? = null,
    val password: String? = null
) {
    fun validateRequest(): String? {
        return when {
            (email.isNullOrBlank() || (userName.isNullOrBlank()) || (password.isNullOrBlank())) -> "Fields should not be blank"
            (!email.isEmailValid()) -> "Email invalid"
            (!userName.isAlphaNumeric()) -> "No special characters allowed in username"
            (userName.length !in (4..30)) -> "Username should be of min 4 and max 30 character in length"
            (password.length !in (8..50)) -> "Password should be of min 8 and max 50 character in length"
//            (confirmPassword.length !in (8..50)) -> "Password should be of min 8 and max 50 character in length"
//            (password != confirmPassword) -> "Passwords do not match"
            else -> null
        }
    }
}
