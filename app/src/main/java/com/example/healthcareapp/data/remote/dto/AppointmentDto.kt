package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class AppointmentDto(
    @Json(name = "id") val id : Int ?= null,
    @Json(name = "date") val date : LocalDate ?= null,
    @Json(name = "hours") val hours : String ?= null,
    @Json(name = "doctor") val doctor : String ?= null,
    @Json(name = "patient") val patient : String ?= null
)
