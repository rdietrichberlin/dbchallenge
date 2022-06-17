package com.deutschebahn.movieapi.internal

import com.deutschebahn.movieapi.model.ApiResponse
import com.deutschebahn.movieapi.model.Failure
import retrofit2.Response
import java.io.IOException

internal inline fun <T, R> safeCall(
    failureHandler: (Int, String) -> Failure,
    exceptionHandler: (Throwable) -> Failure,
    transform: (T?) -> R,
    block: () -> Response<T>
): ApiResponse<R> = try {
    val response = block()
    when (response.isSuccessful) {
        true -> ApiResponse(transform(response.body()))
        else -> {
            ApiResponse(
                errorBody2Failure(
                    response = response,
                    errorBytes = response.errorBody()?.bytes(),
                    failureHandler = failureHandler
                )
            )
        }
    }
} catch (exception: IOException) {
    ApiResponse(exceptionHandler(exception))
}

internal inline fun <T> errorBody2Failure(
    response: Response<T>,
    errorBytes: ByteArray?,
    failureHandler: (Int, String) -> Failure
) = failureHandler(response.code(), errorBytes.toString())