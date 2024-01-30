package com.example.healthcareapp.data.remote.mapper

import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.data.remote.dto.ResultInfoDto
import com.example.healthcareapp.domain.model.ResultInfo

fun ResultInfoDto.toResultInfo() : ResultInfo {
    return ResultInfo(
        analysis = this.analysis.toNonNull(),
        results = this.results.toNonNull(),
        referenceValues = this.referenceValues.toNonNull(),
        units = this.units.toNonNull()
    )
}