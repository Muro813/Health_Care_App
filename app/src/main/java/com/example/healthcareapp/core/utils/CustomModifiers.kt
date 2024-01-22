package com.example.healthcareapp.core.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.ui.theme.HealthCareTheme

object CustomModifiers {

    val numberKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    }

    val numberKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        )
    }

    val textKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    }

    val textKeyboardNextCapitalizeWord: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words
        )
    }

    val textKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    }

    val emailKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        )
    }

    val emailKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    }

    val passwordKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    }

    val passwordKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        )
    }

    val keyboardActions: @Composable () -> KeyboardActions = {
        val focusManager = LocalFocusManager.current
        KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onPrevious = { focusManager.moveFocus(FocusDirection.Up) }
        )
    }

    val snackBarHost: @Composable SnackbarHostState.() -> Unit = {
        SnackbarHost(hostState = this) { snackBarData ->
            Snackbar(
                snackbarData = snackBarData,
                backgroundColor = HealthCareTheme.colors.white,
                contentColor = HealthCareTheme.colors.darkBlue,
                actionColor = HealthCareTheme.colors.secondaryColor,
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}