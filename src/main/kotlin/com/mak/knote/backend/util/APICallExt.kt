package com.mak.knote.backend.util

import com.mak.knote.backend.base.NoteException
import com.mak.knote.backend.base.SomethingWentWrongException

internal suspend fun <TResponse> internalRun(
    remote: suspend () -> TResponse,
): TResponse {
    try {
        return remote.invoke()
    } catch (t: Throwable) {
        if (t is NoteException) throw t
        else throw SomethingWentWrongException("Something went wrong")
    }
}