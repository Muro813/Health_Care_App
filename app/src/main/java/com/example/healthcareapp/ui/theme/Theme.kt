package com.example.healthcareapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun HealthCareTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalHealthCareColors provides healthCareColors,
        LocalHealthCareTypography provides healthCareTypography,
        content = content
    )
}

object HealthCareTheme {
    val colors: HealthCareColors
        @Composable get() = LocalHealthCareColors.current
    val typography: HealthCareTypography
        @Composable get() = LocalHealthCareTypography.current
}
