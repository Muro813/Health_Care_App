package com.example.healthcareapp.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.healthcareapp.BuildConfig.BASE_URL
import com.example.healthcareapp.core.utils.Constants.NO_INFO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import kotlin.math.PI
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

fun String?.toNonNull() = if (this.isNullOrEmpty()) NO_INFO else this

fun String?.toFullGenderNonNull() : String{
    if (this.isNullOrEmpty()) return NO_INFO
    if(this.contains("M")) return "Male"
    if(this.contains("F")) return "Female"
    return "Other"
}

fun Int?.toNonNull() = this ?: -1
fun Boolean?.toBooleanNonNull() : Boolean{
    return this ?: false
}

fun Double?.toNonNull() = this ?: -1.0

fun String.isToken() = this != NO_INFO

fun Long.toTimeFormat() : String{
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(this))
}
fun String.dropDigits() : String {
    return this.filter {
        it.isLetter()
    }
}

infix fun Request.signWithToken(accessToken: String?): Request {
    val builder = newBuilder()
        .header("Accept", "application/json")
        .header("Connection","close")
    /**
    this is a workaround for an issue linked
    https://stackoverflow.com/questions/45838774/java-io-ioexception-unexpected-end-of-stream-on-connection-in-android
    more issues say it's server side
     */
    if (this.url.toString()
            .contains(BASE_URL) && !accessToken.isNullOrEmpty() && !this.url.encodedPath.contains(
            "login"
        )
    ) {
        builder.header("Authorization", "Bearer $accessToken")
    }
    return builder.build()
}

fun String.isValidPassword() : Boolean {
    if(this.length < 8) return false
    val containsLowerCase = any { it.isLowerCase() }
    val containsUpperCase = any { it.isUpperCase() }
    val containsDigit = any { it.isDigit() }
    val containsSpecialChar = any { it.isLetterOrDigit().not() }
    return containsLowerCase && containsUpperCase && containsDigit && containsSpecialChar
}
fun String.isValidEmail() : Boolean{
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    return matches(emailRegex)
}

fun String.isValidWeight() : Boolean{
    return if(this.isEmpty()) false
    else{
        val weight = this.toInt()
        (weight in 40..500)
    }
}

fun String.isValidHeight() : Boolean{
    return if(this.isEmpty()) false
    else{
        val height = this.toInt()
        (height in 80..230)
    }
}

fun String.isValidAge() : Boolean {
    return if(this.isEmpty()) false
    else{
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val age = this.toInt()
        (age in 1900 .. currentYear-18)
    }
}

fun Boolean?.toNonNull() = this != null

suspend inline fun <T> Flow<Resource<T>>.collectLatestWithLoadingNoAuthCheck(
    crossinline onSuccess: suspend (Resource<T>) -> Unit,
    crossinline onError: suspend (Resource<T>) -> Unit,
    crossinline onLoading: suspend () -> Unit,
) {
    withContext(NonCancellable) {
        collectLatest {
            when (it) {
                is Resource.Success -> onSuccess(it)
                is Resource.Error -> {
                    onError(it)
                }

                is Resource.Loading -> {
                    onLoading()
                }
            }
        }
    }
}

suspend inline fun <T> Flow<Resource<T>>.collectLatestNoAuthCheck(
    crossinline onSuccess: suspend (Resource<T>) -> Unit,
    crossinline onError: suspend (Resource<T>) -> Unit,
) {
    collectLatest {
        when (it) {
            is Resource.Success -> onSuccess(it)
            is Resource.Error -> onError(it)
            else -> Unit
        }
    }
}

@Composable
inline fun <reified T> Flow<T>.observeWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState).collect(action)
        }
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

inline fun Modifier.noRippleClickableOnce(crossinline onClick: () -> Unit): Modifier = composed {
    //Solves button spamming if the button should only be pressed once
    var enabled by remember { mutableStateOf(true) }
    clickable(enabled = enabled, indication = null, interactionSource = remember { MutableInteractionSource() }) {
        onClick()
        enabled = false
    }
}