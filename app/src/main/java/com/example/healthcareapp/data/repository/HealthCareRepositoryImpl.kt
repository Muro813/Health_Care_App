package com.example.healthcareapp.data.repository

import com.example.healthcareapp.core.base.BaseDataSource
import com.example.healthcareapp.core.utils.Resource
import com.example.healthcareapp.data.remote.dto.ApiErrorDto
import com.example.healthcareapp.data.remote.dto.AppointmentRequestDto
import com.example.healthcareapp.data.remote.dto.AuthRequestDto
import com.example.healthcareapp.data.remote.dto.AuthResponseDto
import com.example.healthcareapp.data.remote.mapper.toEvent
import com.example.healthcareapp.data.remote.services.ApiService
import com.example.healthcareapp.domain.model.Events
import com.example.healthcareapp.domain.repository.HealthCareRepository
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class HealthCareRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    errorAdapter: JsonAdapter<ApiErrorDto>
) : HealthCareRepository, BaseDataSource(errorAdapter) {
    override suspend fun login(
        username: String,
        password: String
    ): Flow<Resource<AuthResponseDto>> = retrieveFlow{
        apiService.login(AuthRequestDto(
            username = username,
            password = password
        ))
    }

    override suspend fun makeAppointment(date: LocalDate, option: String): Flow<Resource<Events>> = retrieveFlow {
        apiService.makeAppointment(AppointmentRequestDto(date = date, option = option))
    }.mapResponse {
        toEvent()
    }
}
