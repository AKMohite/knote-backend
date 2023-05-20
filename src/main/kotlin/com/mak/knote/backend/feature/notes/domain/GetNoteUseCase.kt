package com.mak.knote.backend.feature.notes.domain

import com.mak.knote.backend.base.BaseResponse
import com.mak.knote.backend.base.BaseUseCase
import com.mak.knote.backend.feature.notes.repository.INotesRepository

internal class GetNoteUseCase(
    private val repository: INotesRepository
) : BaseUseCase<Triple<String, Int, Int>, String> {

    override suspend fun invoke(request: Triple<String, Int, Int>): BaseResponse<String> {
        val (userId, page, limit) = request
        TODO("Not implemented")
    }
}