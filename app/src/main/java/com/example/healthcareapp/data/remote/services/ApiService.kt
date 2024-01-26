package com.example.healthcareapp.data.remote.services

import com.example.healthcareapp.data.remote.dto.AppointmentRequestDto
import com.example.healthcareapp.data.remote.dto.AppointmentResponseDto
import com.example.healthcareapp.data.remote.dto.AuthRequestDto
import com.example.healthcareapp.data.remote.dto.AuthResponseDto
import com.example.healthcareapp.data.remote.dto.DoctorDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/login")
    suspend fun login(@Body authenticationRequestDto: AuthRequestDto) : Response<AuthResponseDto>

    @POST("/api/appointment")
    suspend fun makeAppointment(@Body appointmentReqDto : AppointmentRequestDto) : Response<AppointmentResponseDto>

    @GET("/api/doctor")
    suspend fun getDoctor() : Response<DoctorDto>

    @GET("/api/appointment")
    suspend fun getAppointments() : Response<AppointmentResponseDto>
//    @GET("/api/is-taken")
//    suspend fun isEmailTaken(@Query("email") email : String) : Response<Any>
//    @PUT("/api/user/profile-management/{id}")
//    suspend fun updateChoice(@Path("id") id : Int) : Response<Any>
}