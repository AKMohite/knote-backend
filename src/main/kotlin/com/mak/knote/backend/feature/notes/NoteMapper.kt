package com.mak.knote.backend.feature.notes

suspend fun NoteEntity.toNoteDTO(): NoteDTO {
    return NoteDTO(
        id = id,
        title = title,
        description = description,
        color = color,
        createdBy = createdBy,
        isDeleted = isDeleted,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}