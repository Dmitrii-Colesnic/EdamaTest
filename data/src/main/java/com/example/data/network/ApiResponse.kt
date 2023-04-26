package com.example.data.network

sealed interface ApiResponse<T : Any>

class ResponseSuccess<T : Any>(val data: T) : ApiResponse<T>
class ResponseError<T : Any>(val code: Int, val message: String?) : ApiResponse<T>
class ResponseException<T : Any>(val e: Throwable) : ApiResponse<T>
