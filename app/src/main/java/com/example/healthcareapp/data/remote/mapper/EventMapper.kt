package com.example.healthcareapp.data.remote.mapper

import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.data.remote.dto.AppointmentDto
import com.example.healthcareapp.data.remote.dto.AppointmentOptionsDto
import com.example.healthcareapp.data.remote.dto.AppointmentResponseDto
import com.example.healthcareapp.domain.model.Appointment
import com.example.healthcareapp.domain.model.AppointmentOptions
import com.example.healthcareapp.domain.model.Events

fun AppointmentResponseDto.toEvent() : Events{
    return Events(
        appointments = this.appointments.toAppointments(),
        options = this.options.toOptions()
    )
}

fun List<AppointmentDto>?.toAppointments() : List<Appointment>{
    val result = mutableListOf<Appointment>()
    this?.forEach{
        result.add(
            Appointment(
                id = it.id.toNonNull(),
                date = it.date.toNonNull(),
                hour = it.hours.toNonNull(),
                doctor = it.doctor.toNonNull(),
                patient = it.patient.toNonNull()
            )
        )
    }
    return result
}

fun List<AppointmentOptionsDto>?.toOptions() : List<AppointmentOptions>{
    val result = mutableListOf<AppointmentOptions>()
    this?.forEach {
        result.add(
            AppointmentOptions(
                date = it.date.toNonNull(),
                hours = it.options.toNonNull()
            )
        )
    }
    return result
}