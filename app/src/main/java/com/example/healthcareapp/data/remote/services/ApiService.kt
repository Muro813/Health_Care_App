package com.example.healthcareapp.data.remote.services

import com.example.healthcareapp.data.remote.dto.AuthRequestDto
import com.example.healthcareapp.data.remote.dto.AuthResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/login")
    suspend fun login(@Body authenticationRequestDto: AuthRequestDto) : Response<AuthResponseDto>
//    @GET("/api/is-taken")
//    suspend fun isEmailTaken(@Query("email") email : String) : Response<Any>
//    @PUT("/api/user/profile-management/{id}")
//    suspend fun updateChoice(@Path("id") id : Int) : Response<Any>
}