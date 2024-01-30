package com.example.healthcareapp.data.remote.services

import com.example.healthcareapp.data.remote.dto.AppointmentRequestDto
import com.example.healthcareapp.data.remote.dto.AppointmentResponseDto
import com.example.healthcareapp.data.remote.dto.AuthRequestDto
import com.example.healthcareapp.data.remote.dto.AuthResponseDto
import com.example.healthcareapp.data.remote.dto.DoctorDto
import com.example.healthcareapp.data.remote.dto.RecipeDto
import com.example.healthcareapp.data.remote.dto.ResultDto
import com.example.healthcareapp.data.remote.dto.ResultInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/login")
    suspend fun login(@Body authenticationRequestDto: AuthRequestDto) : Response<AuthResponseDto>

    @POST("/api/appointment")
    suspend fun makeAppointment(@Body appointmentReqDto : AppointmentRequestDto) : Response<AppointmentResponseDto>

    @GET("/api/doctor")
    suspend fun getDoctor() : Response<DoctorDto>

    @GET("/api/appointment")
    suspend fun getAppointments() : Response<AppointmentResponseDto>

    @GET("/api/results")
    suspend fun getResults() : Response<List<ResultDto>>
    @GET("/api/results/{id}")
    suspend fun getResultById(@Path("id") id : Int) : Response<ResultInfoDto>

    @GET("/api/recipes")
    suspend fun getRecipes() : Response<List<RecipeDto>>
}