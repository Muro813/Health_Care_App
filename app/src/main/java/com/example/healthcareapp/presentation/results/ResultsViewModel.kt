package com.example.healthcareapp.presentation.results

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.core.utils.collectLatestNoAuthCheck
import com.example.healthcareapp.core.utils.collectLatestWithLoadingNoAuthCheck
import com.example.healthcareapp.domain.model.Events
import com.example.healthcareapp.domain.model.Result
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val healthCareRepository: HealthCareRepository
) : ViewModel() {
    var state by mutableStateOf(ResultsState())
    private val _snackBarChannel = Channel<String>()
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

    init {
//        getResults()
    }

    private fun getResults() {
        viewModelScope.launch{
            healthCareRepository.getResults().collectLatestWithLoadingNoAuthCheck(
                onSuccess = { res ->
                    res.data?.let {
                        state = state.copy(results = it,shouldShowLoader = false)
                    }
                },
                onError = {
                    it.message?.let {  mess ->
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

    fun onEvent(event: ResultsEvent){
        when(event){
            is ResultsEvent.OnResultClick -> {
                navigateToResultInfoScreen(event.id)
            }
        }
    }

    private fun navigateToResultInfoScreen(id : Int) {
        viewModelScope.launch {
            navigator.navigateTo(Screen.ResultInfoScreen.passResultId(
                resultId = id
            ))
        }
    }
}
data class ResultsState(
    val results : List<Result> = listOf(
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        Result(id = 0, title = "Krvna slika", date = "25 Januar", lab = "Moj lab"),
        ),
    val shouldShowLoader : Boolean = false
)

sealed class ResultsEvent{
    data class OnResultClick(val id : Int) : ResultsEvent()
}