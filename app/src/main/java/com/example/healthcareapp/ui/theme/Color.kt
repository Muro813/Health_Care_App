package com.example.healthcareapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

val PrimaryColor = Color(0xFF29FFC6)
val SecondaryColor = Color(0xFF20E3B2)
val DarkBlue = Color(0xFF163D5D)
val DarkerGray = Color(0xFFF6F5F5)
val Gray = Color(0xFFFAFAFA)
val Green = Color(0xFF00EAC3)
val Yellow = Color(0xFFFFE073)
val Red = Color(0xFFFF6666)
val Orange = Color(0xFFFFAE73)
val White = Color(0xFFFFFFFF)
val Blue = Color(0xFF89CFF0)

@Immutable
data class HealthCareColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val darkBlue : Color,
    val darkerGray : Color,
    val gray : Color,
    val green : Color,
    val yellow : Color,
    val red : Color,
    val orange : Color,
    val white : Color,
    val blue : Color
)

val LocalHealthCareColors = staticCompositionLocalOf {
    HealthCareColors(
        primaryColor = Color.Unspecified,
        secondaryColor = Color.Unspecified,
        darkBlue = Color.Unspecified,
        darkerGray = Color.Unspecified,
        gray = Color.Unspecified,
        green = Color.Unspecified,
        yellow = Color.Unspecified,
        red = Color.Unspecified,
        orange = Color.Unspecified,
        white = Color.Unspecified,
        blue = Color.Unspecified
    )
}

val healthCareColors = HealthCareColors(
    primaryColor = PrimaryColor,
    secondaryColor = SecondaryColor,
    darkBlue = DarkBlue,
    darkerGray = DarkerGray,
    gray = Gray,
    green = Green,
    yellow = Yellow,
    red = Red,
    orange = Orange,
    white = White,
    blue = Blue
)