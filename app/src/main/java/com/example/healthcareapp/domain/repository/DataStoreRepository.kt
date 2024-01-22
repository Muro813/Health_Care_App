package com.example.healthcareapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoarding()
    suspend fun getOnBoarding(): Flow<Boolean>
}