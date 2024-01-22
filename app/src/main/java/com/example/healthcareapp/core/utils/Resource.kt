package com.example.healthcareapp.core.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val isUnauthorized: Boolean? = false,
) {
    class Success<T>(data: T?, message: String? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String?, isUnauthorized: Boolean? = false) :
        Resource<T>(message = message, isUnauthorized = isUnauthorized)
}