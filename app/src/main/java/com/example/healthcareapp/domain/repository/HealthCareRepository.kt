package com.example.healthcareapp.domain.repository

import com.example.healthcareapp.core.utils.Resource
import com.example.healthcareapp.data.remote.dto.AuthResponseDto
import kotlinx.coroutines.flow.Flow

interface HealthCareRepository {
    suspend fun login(username : String, password : String) : Flow<Resource<AuthResponseDto>>
}