package com.mak.knote.backend.feature.notes.domain

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.feature.notes.NoteDTO
import com.mak.knote.backend.feature.notes.repository.INotesRepository

internal class DeleteNoteUseCase(
    private val repository: INotesRepository
) : BaseUseCase<Pair<String, String>, NoteDTO> {

    override suspend fun invoke(request: Pair<String, String>): BaseResponse<NoteDTO> {

        val (userId, noteId) = request
        return repository.deleteNote(userId, noteId)
    }
}
