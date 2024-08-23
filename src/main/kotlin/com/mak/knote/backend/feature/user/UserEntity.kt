package com.mak.knote.backend.feature.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserEntity(
    @BsonId val _id: String = ObjectId().toString(),
    val userName: String,
    val email: String,
    val password: String,
    val isDeleted: Boolean,
    val createdAt: String,
    val updatedAt: String
)
