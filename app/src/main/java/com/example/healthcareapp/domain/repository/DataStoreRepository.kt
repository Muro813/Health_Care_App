package com.example.healthcareapp.domain.repository

import com.example.healthcareapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveToken(token : String)
    suspend fun getToken(): Flow<String>
    suspend fun saveUser(user : User)
    suspend fun getUser() : Flow<User>
}