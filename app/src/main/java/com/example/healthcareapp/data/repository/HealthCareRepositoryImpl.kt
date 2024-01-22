package com.example.healthcareapp.data.repository

import com.example.healthcareapp.core.base.BaseDataSource
import com.example.healthcareapp.core.utils.Resource
import com.example.healthcareapp.data.remote.dto.ApiErrorDto
import com.example.healthcareapp.data.remote.services.ApiService
import com.example.healthcareapp.domain.repository.HealthCareRepository
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HealthCareRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    errorAdapter: JsonAdapter<ApiErrorDto>
) : HealthCareRepository, BaseDataSource(errorAdapter) {
    override suspend fun isEmailTaken(email: String): Flow<Resource<Any>> {
        TODO("Not yet implemented")
    }
}
