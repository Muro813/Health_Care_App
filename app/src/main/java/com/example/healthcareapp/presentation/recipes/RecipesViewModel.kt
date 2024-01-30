package com.example.healthcareapp.presentation.recipes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.utils.collectLatestWithLoadingNoAuthCheck
import com.example.healthcareapp.domain.model.Medicine
import com.example.healthcareapp.domain.model.Recipe
import com.example.healthcareapp.domain.repository.DataStoreRepository
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val navigator: Navigator,
    private val healthCareRepository: HealthCareRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    var state by mutableStateOf(RecipesState())
    private val _snackBarChannel = Channel<String>()
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

    init {
//        getRecipes()
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val role = dataStoreRepository.getUser().first().role
            state = state.copy(role = role)
        }
    }

    private fun getRecipes() {
        viewModelScope.launch{
            healthCareRepository.getRecipes().collectLatestWithLoadingNoAuthCheck(
                onSuccess = { res ->
                    res.data?.let {
                        state = state.copy(recipes = it.toMutableList(),shouldShowLoader = false)
                    }
                },
                onError = {
                    it.message?.let { mess ->
                        _snackBarChannel.send(mess)
                        state = state.copy(shouldShowLoader = false)
                    }
                },
                onLoading = {
                    state = state.copy(shouldShowLoader = true)
                }
            )
        }
    }

}

data class RecipesState(
    val recipes : MutableList<Recipe> = mutableListOf(
        Recipe(
            medicines = mutableListOf(
                Medicine("Medicine1", "5 dana", "500mg, 2 puta dnevno"),
                Medicine("Medicine2", "10 dana", "250mg, 3 puta dnevno"),
                Medicine("Medicine3", "7 dana", "100mg, 1 put dnevno"),
                Medicine("Medicine4", "14 dana", "200mg, 2 puta dnevno"),
                Medicine("Medicine5", "3 dana", "150mg, 3 puta dnevno"),
                Medicine("Medicine6", "6 dana", "300mg, 1 put dnevno"),
                Medicine("Medicine7", "8 dana", "350mg, 2 puta dnevno"),
                Medicine("Medicine8", "9 dana", "400mg, 3 puta dnevno"),
                Medicine("Medicine9", "12 dana", "450mg, 1 put dnevno"),
                Medicine("Medicine10", "15 dana", "600mg, 2 puta dnevno")
            ),
            patient = "Marko pacijent"
        )
    ),
    val shouldShowLoader : Boolean = false,
    val role : Int = -1
)