package com.example.healthcareapp.domain.model

data class Recipe(
    val medicines : MutableList<Medicine>,
    val patient : String
)
