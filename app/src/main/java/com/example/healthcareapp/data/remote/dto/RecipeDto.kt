package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDto(
    @Json(name = "medicines") val medicines : List<MedicineDto> ?= null,
    @Json(name = "patient") val patient : String ?= null
)
