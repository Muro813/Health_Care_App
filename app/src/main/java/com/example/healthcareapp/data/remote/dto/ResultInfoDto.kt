package com.example.healthcareapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultInfoDto(
    @Json(name = "analysis") val analysis : List<String> ?= null,
    @Json(name = "results") val results : List<String> ?= null,
    @Json(name = "referenceValues") val referenceValues : List<String> ?= null,
    @Json(name = "units") val units : List<String> ?= null,
)
