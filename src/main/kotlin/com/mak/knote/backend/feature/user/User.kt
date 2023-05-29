package com.mak.knote.backend.feature.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

internal data class User(
    @BsonId
    val id: String = ObjectId().toString(),
    val email: String? = null,
//    @Transient
    val passwordHash: String? = null,
    val isDeleted: Boolean? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
) {
    fun asResponse(): User {
        return User(
            id = id,
            email = email,
            passwordHash = null,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
