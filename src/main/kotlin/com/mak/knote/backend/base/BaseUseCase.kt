package com.mak.knote.backend.base

/** A Use Case that takes an argument and returns a result. */
internal interface BaseUseCase<in I, R : Any> {
    /** Executes this use case with given input. */
    suspend operator fun invoke(request: I): BaseResponse<R>
}
