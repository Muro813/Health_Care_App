package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppointmentResponseDto(
    @Json(name = "appointments") val appointments : List<AppointmentDto> ?= null,
    @Json(name = "options") val options : List<AppointmentOptionsDto> ?= null
)
