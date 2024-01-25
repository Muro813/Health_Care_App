package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class AppointmentRequestDto(
    @Json(name ="date") val date : LocalDate ?= null,
    @Json(name = "option") val option : String ?= null
)
