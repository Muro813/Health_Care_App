package com.example.healthcareapp.core.base

import com.example.healthcareapp.core.utils.Constants.CHECK_CONNECTION
import com.example.healthcareapp.core.utils.Constants.NETWORK_PROBLEM
import com.example.healthcareapp.core.utils.Resource
import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.data.remote.dto.ApiErrorDto
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.io.IOException

open class BaseDataSource constructor(
    private val errorAdapter: JsonAdapter<ApiErrorDto>
) {
    protected suspend fun <T> retrieveFlow(
        call: suspend () -> Response<T>
    ) = flow {
        emit(Resource.Loading())
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                emit(Resource.Success(data = body))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = errorAdapter.fromJson(errorBody.toNonNull())?.message

                emit(Resource.Error(message = errorMessage))

            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) {
                emit(Resource.Error(CHECK_CONNECTION))
            } else {
                emit(Resource.Error(NETWORK_PROBLEM))
            }
        }
    }.flowOn(Dispatchers.IO)

    fun <T : Any, E : Any> Flow<Resource<T>>.mapResponse(mapperCallback: T.() -> E) = this.map {
        when (it) {
            is Resource.Success -> Resource.Success(
                data = it.data?.mapperCallback()
            )
            is Resource.Error -> Resource.Error(it.message, it.isUnauthorized)
            else -> Resource.Loading()
        }
    }
}