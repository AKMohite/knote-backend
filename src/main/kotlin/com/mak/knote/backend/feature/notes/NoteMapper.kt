package com.mak.knote.backend.feature.notes

suspend fun Note.toNoteDTO(): NoteDTO {
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