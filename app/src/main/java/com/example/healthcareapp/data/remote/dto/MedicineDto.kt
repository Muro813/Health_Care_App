package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MedicineDto(
    @Json(name = "name") val name : String ?= null,
    @Json(name = "time_left") val timeLeft : String ?= null,
    @Json(name = "dosage") val dosage : String ?= null
)
