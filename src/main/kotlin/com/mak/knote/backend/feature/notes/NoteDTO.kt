package com.mak.knote.backend.feature.notes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteDTO(
    val id: String? = null,
    val title: String,
    val description: String,
    val color: String,
    @SerialName("created_by")
    val createdBy: String? = null,
    @SerialName("is_deleted")
    val isDeleted: Boolean? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)
