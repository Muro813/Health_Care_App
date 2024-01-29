package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultDto(
    @Json(name = "id") val id : Int ?= null,
    @Json(name = "title") val title : String ?= null,
    @Json(name = "lab") val lab : String ?= null,
    @Json(name = "date") val date : String ?= null
)
