package com.example.healthcareapp.domain.model

data class ResultInfo(
    val analysis : List<String>,
    val results : List<String>,
    val referenceValues : List<String>,
    val units : List<String>
)
