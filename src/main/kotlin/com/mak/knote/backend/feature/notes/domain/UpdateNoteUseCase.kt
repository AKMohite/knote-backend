package com.mak.knote.backend.feature.notes.domain

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.feature.notes.NoteDTO
import com.mak.knote.backend.feature.notes.repository.INotesRepository

internal class UpdateNoteUseCase(
    private val repository: INotesRepository
) : BaseUseCase<Triple<String, String, NoteDTO>, NoteDTO> {

    override suspend fun invoke(request: Triple<String, String, NoteDTO>): BaseResponse<NoteDTO> {

        val (userId, noteId, note) = request
        return repository.updateNote(userId, noteId, note)
    }
}
