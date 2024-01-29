package com.example.healthcareapp.presentation.recipes

import androidx.lifecycle.ViewModel
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val navigator: Navigator,
    private val healthCareRepository: HealthCareRepository
) : ViewModel() {
}