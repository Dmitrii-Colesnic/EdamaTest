package com.example.domain

sealed interface ResponseDomain<T : Any>

class ResponseSuccessDomain<T : Any>(val data: T) : ResponseDomain<T>
class ResponseErrorDomain<T : Any>(val code: Int, val message: String?) : ResponseDomain<T>
class ResponseExceptionDomain<T : Any>(val e: Throwable) : ResponseDomain<T>