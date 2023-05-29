package com.mak.knote.backend.feature.notes

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Note(
    @BsonId
    val id: String = ObjectId().toString(),
    val title: String,
    val description: String,
    val color: String,
    val createdBy: String,
    val isDeleted: Boolean? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
