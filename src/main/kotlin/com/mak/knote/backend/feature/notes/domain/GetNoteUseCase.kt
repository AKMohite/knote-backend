package com.mak.knote.backend.feature.notes.domain

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.feature.notes.NoteDTO
import com.mak.knote.backend.feature.notes.repository.INotesRepository
import com.mak.knote.backend.util.KnoteConstants.PAGINATION_LIMIT

internal class GetNoteUseCase(
    private val repository: INotesRepository
) : BaseUseCase<Triple<String, Int, Int>, List<NoteDTO>> {

    override suspend fun invoke(request: Triple<String, Int, Int>): BaseResponse<List<NoteDTO>> {
        val (userId, page, limit) = request
        val maxLimit = if (limit > PAGINATION_LIMIT) PAGINATION_LIMIT else limit
        return repository.getNotesForUser(userId, page, maxLimit)
    }
}