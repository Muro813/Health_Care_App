package com.example.healthcareapp.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.core.components.FullScreenLoader
import com.example.healthcareapp.ui.theme.HealthCareTheme
import com.example.healthcareapp.R
import com.example.healthcareapp.core.utils.CustomModifiers.keyboardActions
import com.example.healthcareapp.core.utils.CustomModifiers.passwordKeyboardDone
import com.example.healthcareapp.core.utils.CustomModifiers.textKeyboardNext

@Composable
fun LoginScreen(
    viewModel : LoginViewModel
) {
    val state = viewModel.state

    FullScreenLoader(shouldShowLoader = state.shouldShowLoader)

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.username,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.OnUsernameChange(it))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.licna))
                },
                isError = state.usernameError.isNotEmpty(),
                keyboardActions = keyboardActions(),
                keyboardOptions = textKeyboardNext(),
                singleLine = true,
                textStyle = HealthCareTheme.typography.metropolisRegular14,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = HealthCareTheme.colors.darkBlue,
                    unfocusedTextColor = HealthCareTheme.colors.darkBlue ,
                    focusedBorderColor = HealthCareTheme.colors.secondaryColor,
                    unfocusedBorderColor = HealthCareTheme.colors.primaryColor,
                    errorBorderColor = HealthCareTheme.colors.red,
                    errorSupportingTextColor = HealthCareTheme.colors.red,

                ),
                supportingText = {
                    if(state.usernameError.isNotEmpty())
                        Text(
                            text = state.usernameError,
                            style = HealthCareTheme.typography.metropolisRegular14
                    )
                }
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.OnPasswordChange(it))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.password))
                },
                isError = state.passwordError.isNotEmpty(),
                keyboardActions = keyboardActions(),
                keyboardOptions = passwordKeyboardDone(),
                singleLine = true,
                textStyle = HealthCareTheme.typography.metropolisRegular14,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = HealthCareTheme.colors.darkBlue,
                    unfocusedTextColor = HealthCareTheme.colors.darkBlue ,
                    focusedBorderColor = HealthCareTheme.colors.secondaryColor,
                    unfocusedBorderColor = HealthCareTheme.colors.primaryColor,
                    errorBorderColor = HealthCareTheme.colors.red,
                    errorSupportingTextColor = HealthCareTheme.colors.red,

                    ),
                supportingText = {
                    if(state.passwordError.isNotEmpty())
                        Text(
                            text = state.passwordError,
                            style = HealthCareTheme.typography.metropolisRegular14
                        )
                },
                visualTransformation = if(state.shouldShowPassword)VisualTransformation.None else  PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { viewModel.onEvent(LoginEvent.OnShowPassClick) }) {
                        Icon(
                            painter = painterResource(id = if (state.shouldShowPassword) R.drawable.ic_password_visible else R.drawable.ic_password_hidden),
                            contentDescription = null
                        )
                    }
                }
            )

            Button(
                modifier = Modifier
                    .height(55.dp)
                    .width(100.dp),
                onClick = { viewModel.onEvent(LoginEvent.OnLoginClick)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = HealthCareTheme.colors.blue,
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(text = stringResource(id = R.string.login), style = HealthCareTheme.typography.metropolisBold16, color = HealthCareTheme.colors.white)
            }
        }
    }
}