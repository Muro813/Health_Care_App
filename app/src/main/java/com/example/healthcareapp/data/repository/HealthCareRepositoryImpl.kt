package com.example.healthcareapp.data.repository

import com.example.healthcareapp.core.base.BaseDataSource
import com.example.healthcareapp.core.utils.Resource
import com.example.healthcareapp.data.remote.dto.ApiErrorDto
import com.example.healthcareapp.data.remote.dto.AppointmentRequestDto
import com.example.healthcareapp.data.remote.dto.AuthRequestDto
import com.example.healthcareapp.data.remote.dto.AuthResponseDto
import com.example.healthcareapp.data.remote.mapper.toDoctor
import com.example.healthcareapp.data.remote.mapper.toEvent
import com.example.healthcareapp.data.remote.mapper.toRecipe
import com.example.healthcareapp.data.remote.mapper.toResultInfo
import com.example.healthcareapp.data.remote.mapper.toResults
import com.example.healthcareapp.data.remote.services.ApiService
import com.example.healthcareapp.domain.model.Doctor
import com.example.healthcareapp.domain.model.Events
import com.example.healthcareapp.domain.model.Recipe
import com.example.healthcareapp.domain.model.Result
import com.example.healthcareapp.domain.model.ResultInfo
import com.example.healthcareapp.domain.repository.HealthCareRepository
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun getDoctor(): Flow<Resource<Doctor>> = retrieveFlow{
        apiService.getDoctor()
    }.mapResponse{
        toDoctor()
    }

    override suspend fun getAppointments(): Flow<Resource<Events>> = retrieveFlow {
        apiService.getAppointments()
    }.mapResponse {
        toEvent()
    }

    override suspend fun getResults(): Flow<Resource<List<Result>>> = retrieveFlow {
        apiService.getResults()
    }.mapResponse {
        toResults()
    }

    override suspend fun getResultById(id: Int): Flow<Resource<ResultInfo>>  =  retrieveFlow{
        apiService.getResultById(id = id)
    }.mapResponse {
        toResultInfo()
    }

    override suspend fun getRecipes(): Flow<Resource<List<Recipe>>> = retrieveFlow{
        apiService.getRecipes()
    }.mapResponse {
        toRecipe()
    }
}
