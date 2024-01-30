package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "name") val name : String ?= null,
    @Json(name = "role") val role : Int ?= null
)
