package com.mak.knote.backend.di.domain

import com.mak.knote.backend.di.repository.IRepositoryProvider
import com.mak.knote.backend.feature.auth.domain.LoginUseCase
import com.mak.knote.backend.feature.auth.domain.SignupUseCase
import com.mak.knote.backend.feature.notes.domain.CreateNoteUseCase
import com.mak.knote.backend.feature.notes.domain.GetNoteUseCase

internal class DomainProvider(
    private val repositoryProvider: IRepositoryProvider
) : IDomainProvider {
    override fun provideLoginUserUseCase(): LoginUseCase {
        return DomainLocator.provideLoginUserUseCase(repositoryProvider.provideAuthRepository())
    }

    override fun provideSignupUserUseCase(): SignupUseCase {
        return DomainLocator.provideSignupUserUseCase(repositoryProvider.provideAuthRepository())
    }

    override fun provideGetNoteUseCase(): GetNoteUseCase {
        return DomainLocator.provideGetNoteUseCase(repositoryProvider.provideNoteRepository())
    }

    override fun provideCreateNoteUseCase(): CreateNoteUseCase {
        return DomainLocator.provideCreateNoteUseCase(repositoryProvider.provideNoteRepository())
    }
}