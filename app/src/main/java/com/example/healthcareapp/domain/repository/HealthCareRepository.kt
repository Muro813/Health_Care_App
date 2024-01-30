package com.example.healthcareapp.domain.repository

import com.example.healthcareapp.core.utils.Resource
import com.example.healthcareapp.data.remote.dto.AuthResponseDto
import com.example.healthcareapp.domain.model.Doctor
import com.example.healthcareapp.domain.model.Events
import com.example.healthcareapp.domain.model.Recipe
import com.example.healthcareapp.domain.model.Result
import com.example.healthcareapp.domain.model.ResultInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HealthCareRepository {
    suspend fun login(username : String, password : String) : Flow<Resource<AuthResponseDto>>
    suspend fun makeAppointment(date : LocalDate, option : String) : Flow<Resource<Events>>
    suspend fun getDoctor() : Flow<Resource<Doctor>>
    suspend fun getAppointments() : Flow<Resource<Events>>
    suspend fun getResults() : Flow<Resource<List<Result>>>
    suspend fun getResultById(id : Int) : Flow<Resource<ResultInfo>>
    suspend fun getRecipes() : Flow<Resource<List<Recipe>>>
}