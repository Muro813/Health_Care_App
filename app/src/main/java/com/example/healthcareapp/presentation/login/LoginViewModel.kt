package com.example.healthcareapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.domain.repository.DataStoreRepository
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val healthCareRepository: HealthCareRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel(){
    var state by mutableStateOf(LoginState())
    private val _snackBarChannel = Channel<String>()
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            LoginEvent.OnLoginClick -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        //TODO if valid username and password
        executeLogin()
    }

    private fun executeLogin() {
        viewModelScope.launch {
//            healthCareRepository.login()
        }
    }
}

data class LoginState(
    val username : String = String(),
    val password : String = String(),
    val shouldShowLoader : Boolean = false
)

sealed class LoginEvent{
    data object OnLoginClick : LoginEvent()
}