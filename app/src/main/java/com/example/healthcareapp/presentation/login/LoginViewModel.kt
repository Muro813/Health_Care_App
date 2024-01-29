package com.example.healthcareapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.navigation.Routes.ROOT
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.core.utils.Constants.ERROR_MANDATORY
import com.example.healthcareapp.core.utils.Constants.ERROR_PASSWORD
import com.example.healthcareapp.core.utils.collectLatestNoAuthCheck
import com.example.healthcareapp.core.utils.collectLatestWithLoadingNoAuthCheck
import com.example.healthcareapp.domain.repository.DataStoreRepository
import com.example.healthcareapp.domain.repository.HealthCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val healthCareRepository: HealthCareRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel(){
    var state by mutableStateOf(LoginState())

    fun onEvent(event: LoginEvent){
        when(event){
            LoginEvent.OnLoginClick -> {
                submitData()
            }

            is LoginEvent.OnPasswordChange -> {
                val newVal = event.newVal.trim()
                if(newVal.length <= 20) state = state.copy(password = newVal, passwordError = "")
            }

            is LoginEvent.OnUsernameChange -> {
                val newVal = event.newVal.trim()
                if(newVal.length <= 9) state = state.copy(username = newVal, usernameError = "")
            }

            LoginEvent.OnShowPassClick -> {
                state = state.copy(shouldShowPassword = !state.shouldShowPassword)
            }
        }
    }

    private fun submitData() {
        val usernameError = if(state.username.isEmpty()) ERROR_MANDATORY else ""
        val passwordError = if(state.password.length < 8) ERROR_PASSWORD else ""

      //  if(usernameError.isNotEmpty() || passwordError.isNotEmpty()){
        //    state =state.copy(usernameError = usernameError, passwordError = passwordError)
          //  return
       // }

        executeLogin()
    }

    private fun executeLogin() {
        viewModelScope.launch {
            navigator.popUpTo(Screen.HomeScreen.route,ROOT,true)
//            healthCareRepository.login(
//                username = state.username,
//                password = state.password
//            ).collectLatestWithLoadingNoAuthCheck(
//                onSuccess = {
//                    it.data?.let {  response ->
//                        response.token?.let {
//                            dataStoreRepository.saveToken(response.token)
//                            state = state.copy(shouldShowLoader = false)
//                            navigator.popUpTo(Screen.HomeScreen.route,ROOT,true)
//                        }
//                    }
//                },
//                onError = {
//                    it.message?.let { message ->
//                        state = state.copy(passwordError = message, shouldShowLoader = false)
//                    }
//                },
//                onLoading = {
//                    state = state.copy(shouldShowLoader = true)
//                }
//            )
        }
    }
}

data class LoginState(
    val username : String = String(),
    val password : String = String(),
    val usernameError : String = String(),
    val passwordError : String = String(),
    val shouldShowLoader : Boolean = false,
    val shouldShowPassword : Boolean = false
)

sealed class LoginEvent{
    data object OnLoginClick : LoginEvent()
    data class OnUsernameChange(val newVal : String) : LoginEvent()
    data class OnPasswordChange(val newVal : String) : LoginEvent()
    data object OnShowPassClick : LoginEvent()
}