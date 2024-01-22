package com.example.healthcareapp.domain.repository

import com.example.healthcareapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HealthCareRepository {
    suspend fun isEmailTaken(email : String) : Flow<Resource<Any>>
}