package com.example.domain

sealed interface ServerResponse<T : Any>

class ServerResponseSuccess<T : Any>(val responseData: T) : ServerResponse<T>
class ServerResponseError<T : Any>(val responseCode: Int, val responseMessage: String?) : ServerResponse<T>
class ServerResponseException<T : Any>(val exception: Throwable) : ServerResponse<T>