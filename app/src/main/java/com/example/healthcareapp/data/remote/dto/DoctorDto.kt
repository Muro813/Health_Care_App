package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DoctorDto(
    @Json(name = "id") val id : Int ?= null,
    @Json(name = "name") val name : String ?= null,
    @Json(name = "speciality") val speciality : String ?= null,
    @Json(name = "image") val image : String ?= null
)