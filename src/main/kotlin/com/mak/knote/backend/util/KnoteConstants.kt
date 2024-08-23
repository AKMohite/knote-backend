package com.mak.knote.backend.util

internal object KnoteConstants {
    const val APP_NAME = "Knote"

    //    API
    const val BASE_ROUTE = "/v1/api"
    const val LOGIN_ROUTE = "$BASE_ROUTE/auth/login"
    const val SIGNUP_ROUTE = "$BASE_ROUTE/auth/signup"
    const val REFRESH_TOKEN_ROUTE = "$BASE_ROUTE/auth/token/refresh"
    const val NOTES_ROUTE = "$BASE_ROUTE/notes"
    const val NOTEID = "note_id"
    const val SINGLE_NOTES_ROUTE = "$BASE_ROUTE/notes/{$NOTEID}"

    const val PAGINATION_LIMIT = 30

    //    JWT
    const val ACCESS_TOKEN_TYPE = "accessToken"
    const val REFRESH_TOKEN_TYPE = "refreshToken"

    //    DB
    const val DATABASE_NAME = "mak_knotes"
    const val USERS_TABLE = "users"
}