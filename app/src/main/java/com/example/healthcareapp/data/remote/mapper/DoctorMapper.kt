package com.example.healthcareapp.data.remote.mapper

import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.data.remote.dto.DoctorDto
import com.example.healthcareapp.domain.model.Doctor

fun DoctorDto.toDoctor() : Doctor{
    return Doctor(
        id = this.id.toNonNull(),
        name = this.name.toNonNull(),
        image = this.image.toNonNull(),
        speciality = this.speciality.toNonNull()
    )
}