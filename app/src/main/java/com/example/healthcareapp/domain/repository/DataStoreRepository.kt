package com.example.healthcareapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveToken(token : String)
    suspend fun getToken(): Flow<String>
}