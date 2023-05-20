package com.mak.knote.backend.feature.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

internal data class User(
    val email: String? = null,
//    @Transient
    val passwordHash: String? = null,
    @BsonId
    val id: String = ObjectId().toString(),
    val createdAt: String? = null,
    val updatedAt: String? = null
)
