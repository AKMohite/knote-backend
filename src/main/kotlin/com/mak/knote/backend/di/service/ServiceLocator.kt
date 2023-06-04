package com.mak.knote.backend.di.service

import com.mak.knote.backend.feature.notes.Note
import com.mak.knote.backend.feature.notes.service.INotesApiService
import com.mak.knote.backend.feature.notes.service.NotesApiService
import com.mak.knote.backend.feature.user.User
import com.mak.knote.backend.feature.user.service.IUserApiService
import com.mak.knote.backend.feature.user.service.UserApiService
import org.litote.kmongo.coroutine.CoroutineCollection

internal object ServiceLocator {
    fun provideUserApiService(userCollection: CoroutineCollection<User>): IUserApiService {
        return UserApiService(userCollection)
    }

    fun provideServiceProvider(): IServiceProvider {
        return ServiceProvider()
    }

    fun provideNoteApiService(noteCollection: CoroutineCollection<Note>): INotesApiService {
        return NotesApiService(noteCollection)
    }

}
