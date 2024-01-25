package com.example.healthcareapp.domain.model

data class Events(
    val appointments : List<Appointment>,
    val options : List<AppointmentOptions>
)
