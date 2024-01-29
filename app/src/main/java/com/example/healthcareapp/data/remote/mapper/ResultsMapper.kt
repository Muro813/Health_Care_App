package com.example.healthcareapp.data.remote.mapper

import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.data.remote.dto.ResultDto
import com.example.healthcareapp.domain.model.Result

fun List<ResultDto>.toResults() : List<Result> {
    val result = mutableListOf<Result>()
    this.forEach{
        result.add(
            Result(
                id = it.id.toNonNull(),
                title = it.title.toNonNull(),
                lab = it.lab.toNonNull(),
                date = it.date.toNonNull()
            )
        )
    }
    return result
}