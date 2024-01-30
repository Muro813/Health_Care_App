package com.example.healthcareapp.domain.model

import java.time.LocalDate

data class Appointment(
    val id : Int,
    val date : LocalDate = LocalDate.now(),
    val hour : String,
    val doctor : String,
    val patient : String
)
