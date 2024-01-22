package com.example.healthcareapp.data.remote.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("/api/login")
//    suspend fun login(@Body authenticationRequestDto: AuthenticationRequestDto) : Response<AuthenticationResponseDto>
    suspend fun login() : Response<Any>
    @GET("/api/is-taken")
    suspend fun isEmailTaken(@Query("email") email : String) : Response<Any>
    @PUT("/api/user/profile-management/{id}")
    suspend fun updateChoice(@Path("id") id : Int) : Response<Any>
}