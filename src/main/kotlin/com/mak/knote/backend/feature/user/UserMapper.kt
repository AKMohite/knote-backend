package com.mak.knote.backend.feature.user

import com.mak.knote.backend.common.data.dto.request.RegisterDTO
import com.mak.knote.backend.common.data.dto.response.UserDTO
import com.mak.knote.backend.util.requireNonNullable
import java.util.*

internal object UserMapper {
    fun jsonToEntity(json: RegisterDTO): UserEntity {
        val nowInstant = Date().toInstant().toString()
        return UserEntity(
            userName = json.userName.requireNonNullable(),
            email = json.email.requireNonNullable(),
            password = json.password.requireNonNullable(),
            createdAt = nowInstant,
            updatedAt = nowInstant,
            isDeleted = false
        )
    }

    fun entityToModel(entity: UserEntity) = User(
        id = entity._id,
        email = entity.email,
        username = entity.userName,
    )

    fun entityToJson(entity: UserEntity) = UserDTO(
        id = entity._id,
        email = entity.email,
        userName = entity.userName
    )
}
