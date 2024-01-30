package com.example.healthcareapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.healthcareapp.core.utils.noRippleClickable
import com.example.healthcareapp.core.utils.observeWithLifecycle
import com.example.healthcareapp.domain.model.Appointment
import com.example.healthcareapp.domain.model.AppointmentOptions
import com.example.healthcareapp.ui.theme.HealthCareTheme
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import timber.log.Timber
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(
    viewModel : HomeViewModel,
    showSnackBar : (String) -> Unit
) {

    val state = viewModel.state
    viewModel.snackBarChannel.observeWithLifecycle{
        showSnackBar(it)
    }
    OptionsDialog(
        options = state.options,
        selectedDate = state.selectedDate,
        onDismiss = {
            viewModel.onEvent(HomeEvent.OnDismiss)
        },
        onClick = {
            viewModel.onEvent(HomeEvent.OnOptionClick(it))
        },
        shouldShow = state.shouldShowDialog,
        appointments = state.appointments
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
            HomeScreenTopBar(
                name = state.doctor.name,
                image = state.doctor.image,
                speciality = state.doctor.speciality
            )
            Column(
                modifier = Modifier.fillMaxSize(),
            ){
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .padding(horizontal = 32.dp)
                ) {
                    Text(text = "Zakazivanje:", style = HealthCareTheme.typography.metropolisBold20, color = HealthCareTheme.colors.darkBlue.copy(alpha = 0.6f ))
                }
                SelectableCalendar(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(top = 30.dp)
                        .border(
                            1.dp,
                            HealthCareTheme.colors.primaryColor,
                            RoundedCornerShape(28.dp)
                        ),
                    calendarState = rememberSelectableCalendarState(
                        initialSelectionMode = SelectionMode.Single
                    ),
                    firstDayOfWeek = DayOfWeek.MONDAY,
                    horizontalSwipeEnabled = true,
                    dayContent = { dayState ->
                        CustomDay(
                            state = dayState,
                            daysWithStuffOnThem = state.appointments,
                            availableDays = state.options
                        ) { date ->
                            viewModel.onEvent(HomeEvent.OnDateClick(date))
                        }
                    },
                    monthHeader = { monthState -> CustomMonthHeader(monthState = monthState) },
                    weekHeader = { dayOfWeeks -> CustomWeekHeader(daysOfWeek = dayOfWeeks) }
                )
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .padding(horizontal = 32.dp)
                ) {
                    Text(text = "Zakazano:", style = HealthCareTheme.typography.metropolisBold20, color = HealthCareTheme.colors.darkBlue.copy(alpha = 0.6f ))
                    state.appointments.forEach {

                    }
                }
            }

    }
}
@Composable
fun <T : SelectionState> CustomDay(
    state: DayState<T>,
    modifier: Modifier = Modifier,
    isEndOfMonth: Boolean = false,
    daysWithStuffOnThem : List<Appointment> = listOf(),
    availableDays : List<AppointmentOptions> = listOf(),
    onClick: (LocalDate) -> Unit = { date -> },
) {

    val date = state.date

    val selectionState = state.selectionState

    val isAvailableDay = when {
        isEndOfMonth -> {
            val today = LocalDate.now()
            date > today && date <= today.plusDays(62)
        }

        else -> date > LocalDate.now() && date <= LocalDate.now().plusDays(62)
    }

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        elevation = 0.dp,
        shape = RectangleShape,
        backgroundColor =if(daysWithStuffOnThem.find{it.date == date} != null) HealthCareTheme.colors.primaryColor  else HealthCareTheme.colors.white
    ) {
        Box(
            modifier = Modifier.noRippleClickable {
                if (isAvailableDay && (availableDays.find { it.date == date } != null || daysWithStuffOnThem.find { it.date == date } != null)) {
                    onClick(date)
                    selectionState.onDateSelected(date)
                }
            },
            contentAlignment = Alignment.Center,
        ) {
            if (isAvailableDay && (date.month == LocalDate.now().month || date.month == LocalDate.now().month.plus(
                    1
                )) && (availableDays.find { it.date == date } != null || daysWithStuffOnThem.find { it.date == date } != null)
            )
                Text(
                    text = date.dayOfMonth.toString(),
                    style = HealthCareTheme.typography.metropolisRegular14,
                    color = HealthCareTheme.colors.darkBlue
                )

            else Text(
                text = date.dayOfMonth.toString(),
                style = HealthCareTheme.typography.metropolisRegular14,
                color = HealthCareTheme.colors.blue
            )
        }
    }
}

@Composable
fun CustomMonthHeader(
    monthState: MonthState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
    ) {
        val canGoForward = monthState.currentMonth >= YearMonth.now()
            .minusMonths(1) && monthState.currentMonth < YearMonth.now().plusMonths(1)

        val canGoBack = monthState.currentMonth >= YearMonth.now()
            .plusMonths(1) || monthState.currentMonth > YearMonth.now().minusMonths(1)
        Image(
            if (canGoBack) Icons.Default.ArrowBack else Icons.Default.ArrowForward,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(34.dp)
                .noRippleClickable {
                    if (canGoBack)
                        monthState.currentMonth = monthState.currentMonth.minusMonths(1)
                }
                .align(Alignment.CenterStart)
        )
        Image(
            if (canGoForward) Icons.Default.ArrowForward else Icons.Default.ArrowForward,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(34.dp)
                .noRippleClickable {
                    if (canGoForward)
                        monthState.currentMonth = monthState.currentMonth.plusMonths(1)
                }
                .align(Alignment.CenterEnd)
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = monthState.currentMonth.month
                    .getDisplayName(TextStyle.FULL, Locale.getDefault())
                    .uppercase(),
                color = HealthCareTheme.colors.darkBlue,
                style = HealthCareTheme.typography.metropolisRegular18
            )
            Text(
                text = monthState.currentMonth.year.toString(),
                color = HealthCareTheme.colors.blue,
                style = HealthCareTheme.typography.metropolisRegular14
            )
        }
    }
}


@Composable
fun CustomWeekHeader(
    daysOfWeek: List<DayOfWeek>
) {
    Row(modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                style = HealthCareTheme.typography.metropolisRegular14,
                color = HealthCareTheme.colors.darkBlue
            )
        }
    }
}

@Composable
fun OptionsDialog(
    options : List<AppointmentOptions>,
    selectedDate : LocalDate?,
    onDismiss : () -> Unit,
    shouldShow : Boolean,
    appointments : List<Appointment>,
    onClick : (String) -> Unit
) {
    if(selectedDate != null && shouldShow){
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(HealthCareTheme.colors.white, RoundedCornerShape(12.dp))
                .padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(text = selectedDate.toString(), style = HealthCareTheme.typography.metropolisBold16)
                Divider()
                val availableOptions = options.find {
                    it.date == selectedDate
                }
                val appointment = appointments.find {
                    it.date == selectedDate
                }
                if(appointment != null){
                    Text(text = appointment.hour)
                }
                else if(availableOptions == null){
                    Text(text = "Nema termina.")
                }
                else{
                    availableOptions.hours.forEach {
                        Row(modifier = Modifier.noRippleClickable {
                            onClick(it)
                            onDismiss()
                        }){
                            Text(text = it)
                        }
                        Divider()
                    }
                }
            }
        }
    }
}