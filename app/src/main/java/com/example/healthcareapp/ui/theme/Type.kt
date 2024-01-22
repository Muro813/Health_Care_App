package com.example.healthcareapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Immutable
data class HealthCareTypography(
    val metropolisExtraBold32: TextStyle,
    val metropolisBold24: TextStyle,
    val metropolisBold22: TextStyle,
    val metropolisBold20: TextStyle,
    val metropolisBold18: TextStyle,
    val metropolisBold16: TextStyle,
    val metropolisBold14: TextStyle,
    val metropolisBold12: TextStyle,
    val metropolisSemiBold16: TextStyle,
    val metropolisMedium16: TextStyle,
    val metropolisRegular18: TextStyle,
    val metropolisRegular16: TextStyle,
    val metropolisRegular14: TextStyle,
    val metropolisRegular12: TextStyle,
    val metropolisLight: TextStyle
)

val LocalHealthCareTypography = staticCompositionLocalOf {
    HealthCareTypography(
        metropolisExtraBold32 = TextStyle.Default,
        metropolisBold24 = TextStyle.Default,
        metropolisBold22 = TextStyle.Default,
        metropolisBold20 = TextStyle.Default,
        metropolisBold18 = TextStyle.Default,
        metropolisBold16 = TextStyle.Default,
        metropolisBold14 = TextStyle.Default,
        metropolisBold12 = TextStyle.Default,
        metropolisSemiBold16 = TextStyle.Default,
        metropolisMedium16 = TextStyle.Default,
        metropolisRegular18 = TextStyle.Default,
        metropolisRegular16 = TextStyle.Default,
        metropolisRegular14 = TextStyle.Default,
        metropolisRegular12 = TextStyle.Default,
        metropolisLight = TextStyle.Default
    )
}

val healthCareTypography = HealthCareTypography(
    metropolisExtraBold32 = TextStyle(
        fontFamily = metropolisExtraBold,
        fontSize = 32.sp
    ),
    metropolisBold24 = TextStyle(
        fontFamily = metropolisBold,
        fontSize = 24.sp
    ),
    metropolisBold22 = TextStyle(
        fontFamily = metropolisBold,
        fontSize = 22.sp
    ),
    metropolisBold20 = TextStyle(
        fontFamily = metropolisBold,
        fontSize = 20.sp
    ),
    metropolisBold18 = TextStyle(
        fontFamily = metropolisBold,
        fontSize = 18.sp
    ),
    metropolisBold16 = TextStyle(
        fontFamily = metropolisBold,
        fontSize = 16.sp
    ),
    metropolisBold14 = TextStyle(
        fontFamily = metropolisBold,
        fontSize = 14.sp
    ),
    metropolisBold12 = TextStyle(
        fontFamily = metropolisBold,
        fontSize = 12.sp
    ),
    metropolisSemiBold16 = TextStyle(
        fontFamily = metropolisSemiBold,
        fontSize = 16.sp
    ),
    metropolisMedium16 = TextStyle(
        fontFamily = metropolisMedium,
        fontSize = 16.sp
    ),
    metropolisRegular18 = TextStyle(
        fontFamily = metropolisRegular,
        fontSize = 18.sp
    ),
    metropolisRegular16 = TextStyle(
        fontFamily = metropolisRegular,
        fontSize = 16.sp
    ),
    metropolisRegular14 = TextStyle(
        fontFamily = metropolisRegular,
        fontSize = 14.sp
    ),
    metropolisRegular12 = TextStyle(
        fontFamily = metropolisRegular,
        fontSize = 12.sp
    ),
    metropolisLight = TextStyle(
        fontFamily = metropolisLight
    )
)