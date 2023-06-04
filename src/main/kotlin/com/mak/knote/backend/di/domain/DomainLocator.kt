package com.mak.knote.backend.di.domain

import com.mak.knote.backend.di.repository.RepositoryLocator
import com.mak.knote.backend.feature.auth.domain.LoginUseCase
import com.mak.knote.backend.feature.auth.domain.SignupUseCase
import com.mak.knote.backend.feature.auth.repository.IAuthRepository
import com.mak.knote.backend.feature.notes.domain.CreateNoteUseCase
import com.mak.knote.backend.feature.notes.domain.GetNoteUseCase
import com.mak.knote.backend.feature.notes.domain.UpdateNoteUseCase
import com.mak.knote.backend.feature.notes.repository.INotesRepository

internal object DomainLocator {
    fun provideLoginUserUseCase(repository: IAuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    fun provideSignupUserUseCase(repository: IAuthRepository): SignupUseCase {
        return SignupUseCase(repository)
    }

    fun provideDomainProvider(): IDomainProvider {
        return DomainProvider(RepositoryLocator.provideRepositoryProvider())
    }

    fun provideGetNoteUseCase(repository: INotesRepository): GetNoteUseCase {
        return GetNoteUseCase(repository)
    }

    fun provideCreateNoteUseCase(repository: INotesRepository): CreateNoteUseCase {
        return CreateNoteUseCase(repository)
    }

    fun provideUpdateNoteUseCase(repository: INotesRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }


}
