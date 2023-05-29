package com.mak.knote.backend.base

import io.ktor.http.HttpStatusCode

internal interface BaseResponse<T : Any> {
    val statusCode: HttpStatusCode
}

data class SuccessResponse<T : Any>(
    override val statusCode: HttpStatusCode,
    val data: T? = null,
    val message: String? = null
) : BaseResponse<T>

data class UnSuccessResponse<T : Any>(
    override val statusCode: HttpStatusCode,
    val exception: T? = null,
) : BaseResponse<T>

data class PaginatedResponse<T : Any>(
    override val statusCode: HttpStatusCode,
    val prev: Int?,
    val next: Int?,
    val totalCount: Int = 0,
    val totalPages: Int = 0,
    val data: T? = null,
    val message: String? = null
) : BaseResponse<T>