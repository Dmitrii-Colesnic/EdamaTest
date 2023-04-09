package com.example.data.network

import retrofit2.HttpException
import retrofit2.Response

sealed interface ApiResponse<T : Any>

class ResponseSuccess<T : Any>(val data: T) : ApiResponse<T>
class ResponseError<T : Any>(val code: Int, val message: String?) : ApiResponse<T>
class ResponseException<T : Any>(val e: Throwable) : ApiResponse<T>

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResponse<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ResponseSuccess(data = body)
        } else {
            ResponseError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ResponseError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ResponseException(e)
    }
}
