package com.mak.knote.backend.di.service

import com.mak.knote.backend.di.DatabaseLocator
import com.mak.knote.backend.feature.notes.service.INotesApiService
import com.mak.knote.backend.feature.user.service.IUserApiService

internal class ServiceProvider : IServiceProvider {
    override fun provideUserApiService(): IUserApiService {
        return ServiceLocator.provideUserApiService(DatabaseLocator.provideDatabase().userCollection)
    }

    override fun provideNoteApiService(): INotesApiService {
        return ServiceLocator.provideNoteApiService(DatabaseLocator.provideDatabase().noteCollection)
    }
}