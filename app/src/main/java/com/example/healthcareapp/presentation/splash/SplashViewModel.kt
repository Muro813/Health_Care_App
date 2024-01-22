package com.example.healthcareapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.domain.repository.DataStoreRepository
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import com.example.healthcareapp.presentation.splash.SplashEvent.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val navigator: Navigator,
    private val foodynessRepository: HealthCareRepository
) : ViewModel() {
    fun onEvent(event : SplashEvent){
        when(event){
            GoNextScreen -> {
                handleNavigation()
            }
        }
    }
    private fun handleNavigation() {
        viewModelScope.launch {
        }
    }
}

sealed class SplashEvent{
    data object GoNextScreen : SplashEvent()
}