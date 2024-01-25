package com.example.healthcareapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.utils.collectLatestNoAuthCheck
import com.example.healthcareapp.domain.model.Appointment
import com.example.healthcareapp.domain.model.AppointmentOptions
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val healthCareRepository: HealthCareRepository
) : ViewModel() {
    var state by mutableStateOf(HomeState())
    private val _snackBarChannel = Channel<String>()
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnDateClick -> {
                val shouldShowDialog = state.selectedDate != event.date
                state = state.copy(selectedDate = event.date,shouldShowDialog = shouldShowDialog)
            }
            is HomeEvent.OnOptionClick -> {
                selectOption(event.option)
            }

            HomeEvent.OnDismiss -> {
                state = state.copy(shouldShowDialog = false, selectedDate = null)
            }
        }
    }

    private fun selectOption(option: String) {
        viewModelScope.launch {
            state.selectedDate?.let {
                healthCareRepository.makeAppointment(date = it, option = option).collectLatestNoAuthCheck(
                    onSuccess = { res ->
                        res.data?.let{ data ->
                                    state = state.copy(options = data.options, appointments = data.appointments)
                                    _snackBarChannel.send("Success")
                                }
                    },
                    onError = { res ->
                        res.message?.let{ mess ->
                            _snackBarChannel.send( mess)
                        }
                    }
                )
            }
        }
    }
}
data class HomeState(
    val appointments : List<Appointment> = listOf(Appointment(0, LocalDate.now().plusDays(2), hour = "10:30")),
    val selectedDate : LocalDate ?= null,
    val options : List<AppointmentOptions> = listOf(AppointmentOptions(LocalDate.now().plusDays(15),
        listOf("10:00 - 11:00","11:00-12:00")
    )),
    val shouldShowDialog : Boolean = false
)
sealed class HomeEvent{
    data class OnDateClick(val date : LocalDate) : HomeEvent()
    data class OnOptionClick(val option : String) : HomeEvent()
    data object OnDismiss : HomeEvent()
}