package com.mak.knote.backend.common.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDTO(
    val id: String,
    @SerialName("user_name")
    val userName: String,
    val email: String
)
