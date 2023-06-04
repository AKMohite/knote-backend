package com.mak.knote.backend.di.domain

import com.mak.knote.backend.feature.auth.domain.LoginUseCase
import com.mak.knote.backend.feature.auth.domain.SignupUseCase
import com.mak.knote.backend.feature.notes.domain.CreateNoteUseCase
import com.mak.knote.backend.feature.notes.domain.GetNoteUseCase
import com.mak.knote.backend.feature.notes.domain.UpdateNoteUseCase

internal interface IDomainProvider {
    fun provideLoginUserUseCase(): LoginUseCase
    fun provideSignupUserUseCase(): SignupUseCase
    fun provideGetNoteUseCase(): GetNoteUseCase
    fun provideCreateNoteUseCase(): CreateNoteUseCase
    fun provideUpdateNoteUseCase(): UpdateNoteUseCase
}
