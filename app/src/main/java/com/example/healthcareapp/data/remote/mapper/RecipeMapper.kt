package com.example.healthcareapp.data.remote.mapper

import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.data.remote.dto.MedicineDto
import com.example.healthcareapp.data.remote.dto.RecipeDto
import com.example.healthcareapp.domain.model.Medicine
import com.example.healthcareapp.domain.model.Recipe

fun List<RecipeDto>.toRecipe() : List<Recipe>{
    val result = mutableListOf<Recipe>()
    this.forEach{
       result.add(
           Recipe(
            patient = it.patient.toNonNull(),
            medicines = it.medicines.toMedicine()
           )
       )
    }
    return result
}

fun List<MedicineDto>?.toMedicine() : MutableList<Medicine>{
    val result = mutableListOf<Medicine>()
    this?.let { list ->
        list.forEach{
            result.add(
                Medicine(
                    name = it.name.toNonNull(),
                    timeLeft = it.timeLeft.toNonNull(),
                    dosage = it.dosage.toNonNull()
                )
            )
        }
    }
    return result
}