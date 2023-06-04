package com.mak.knote.backend.util

internal object KnoteConstants {
    const val BASE_ROUTE = "/v1/api"
    const val LOGIN_ROUTE = "$BASE_ROUTE/auth/login"
    const val SIGNUP_ROUTE = "$BASE_ROUTE/auth/signup"
    const val NOTES_ROUTE = "$BASE_ROUTE/notes"
    const val NOTEID = "note_id"
    const val SINGLE_NOTES_ROUTE = "$BASE_ROUTE/notes/{$NOTEID}"

    const val PAGINATION_LIMIT = 30
}