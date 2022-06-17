package com.deutschebahn.movieapi.model

sealed class Failure(
    val code: Int,
    val message: String
) {

    object None : Failure(200, "No failure")

    override fun toString() =
        "${javaClass.simpleName} (code=$code, message='$message')"
}

class UnknownError(code: Int, message: String) : Failure(code, message)

/**
 * This class of failure indicates that the REST client stopped with an exception.
 *
 * Http-Statuscode 999 is not officially defined, so it can be used for custom purpose.
 */
class UnexpectedFailure(message: String) : Failure(999, message)