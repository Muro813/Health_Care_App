package com.example.healthcareapp.domain.model

import java.time.LocalDate

data class AppointmentOptions(
    val date : LocalDate,
    val hours : List<String>
)
