package com.mak.knote.backend.di

import com.mak.knote.backend.base.IKnoteDB
import com.mak.knote.backend.base.KnoteDB

internal object DatabaseLocator {
    private fun provideClientName(): String {
        return "ktor-mongo-knote"
    }

    fun provideDatabase(): IKnoteDB {
        return KnoteDB(provideClientName())
    }
}