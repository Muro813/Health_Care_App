package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequestDto(
    @Json(name = "username") val username : String ?= null,
    @Json(name = "password") val password : String ?= null
)
