package com.example.healthcareapp.presentation.login

import androidx.compose.runtime.Composable
import com.example.healthcareapp.core.components.FullScreenLoader
import com.example.healthcareapp.core.utils.observeWithLifecycle

@Composable
fun LoginScreen(
    viewModel : LoginViewModel,
    showSnackBar : (String) -> Unit
) {
    val state = viewModel.state

    viewModel.snackBarChannel.observeWithLifecycle{
        showSnackBar(it)
    }
    FullScreenLoader(shouldShowLoader = state.shouldShowLoader)


}