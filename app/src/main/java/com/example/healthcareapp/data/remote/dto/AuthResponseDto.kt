package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponseDto(
    @Json(name = "token ") val token : String ?= null,
    @Json(name = "role") val role : Int ?= null
)
