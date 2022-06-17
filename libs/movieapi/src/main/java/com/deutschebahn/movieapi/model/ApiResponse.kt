package com.deutschebahn.movieapi.model

import androidx.annotation.Keep

@Keep
class ApiResponse<T>
// We want to avoid situations where we create an ApiResponse without Failure nor Result.
private constructor(val failure: Failure, val result: T?) {

    constructor(result: T) : this(Failure.None, result)
    constructor(failure: Failure) : this(failure, null as T?)

    val hasFailure: Boolean by lazy { failure != Failure.None }
    val hasResult: Boolean by lazy { result != null }

    inline fun <R> fold(onFailure: (Failure) -> R, onSuccess: (T) -> R): R = when {
        hasResult -> onSuccess(result!!)
        else -> onFailure(failure)
    }
}