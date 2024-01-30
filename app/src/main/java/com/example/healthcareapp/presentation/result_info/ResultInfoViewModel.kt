package com.example.healthcareapp.presentation.result_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.utils.collectLatestWithLoadingNoAuthCheck
import com.example.healthcareapp.domain.model.ResultInfo
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultInfoViewModel @Inject constructor(
    private val navigator: Navigator,
    private val healthCareRepository: HealthCareRepository
) : ViewModel(){
    var state by mutableStateOf(ResultInfoState())
    private val _snackBarChannel = Channel<String>()
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

    fun onEvent(event: ResultInfoEvent){
        when(event){
            ResultInfoEvent.OnBackClick -> {
                viewModelScope.launch {
                    navigator.popBackStack()
                }
            }

            is ResultInfoEvent.GetData -> {
//                getData(id = event.id)
            }
        }
    }

    private fun getData(id: Int) {
            viewModelScope.launch{
                healthCareRepository.getResultById(id = id).collectLatestWithLoadingNoAuthCheck(
                    onSuccess = { res ->
                        res.data?.let {
                            state = state.copy(results = it, shouldShowLoader = false)
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

data class ResultInfoState(
    val results : ResultInfo = ResultInfo(
        analysis = listOf("Glukoza","Kreatinin","Mokraćna kiselina"),
        results = listOf("H 6.2","L 58","300.6"),
        referenceValues  = listOf("4.11~5.89","62~106","202.3~416.5"),
        units = listOf("%","µmol/L","µmol/L"),
    ),
    val shouldShowLoader : Boolean = false
)

sealed class ResultInfoEvent{
    data object OnBackClick : ResultInfoEvent()
    data class GetData(val id : Int) : ResultInfoEvent()
}