package com.mak.knote.backend.feature.notes.domain

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.feature.notes.NoteDTO
import com.mak.knote.backend.feature.notes.repository.INotesRepository

internal class CreateNoteUseCase(
    private val repository: INotesRepository
) : BaseUseCase<Pair<String, NoteDTO>, NoteDTO> {

    override suspend fun invoke(request: Pair<String, NoteDTO>): BaseResponse<NoteDTO> {

        val (userId, note) = request
        return repository.createNote(userId, note)
    }
}