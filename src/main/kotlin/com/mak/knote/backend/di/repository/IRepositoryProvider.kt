package com.mak.knote.backend.di.repository

import com.mak.knote.backend.feature.auth.repository.IAuthRepository
import com.mak.knote.backend.feature.notes.repository.INotesRepository

internal interface IRepositoryProvider {
    fun provideAuthRepository(): IAuthRepository
    fun provideNoteRepository(): INotesRepository
}
