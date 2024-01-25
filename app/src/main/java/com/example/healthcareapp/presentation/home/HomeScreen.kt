package com.example.healthcareapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.core.utils.noRippleClickable
import com.example.healthcareapp.ui.theme.HealthCareTheme
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
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
    LazyColumn(){
//        HomeScreenTopBar()
        item {
            SelectableCalendar(
                calendarState = rememberSelectableCalendarState(
                    initialSelectionMode = SelectionMode.Single
                ),
                firstDayOfWeek = DayOfWeek.MONDAY,
                horizontalSwipeEnabled = true,
                dayContent = { dayState ->
                    CustomDay(state = dayState) { date, isSelected ->
//                        onDateChanged(date.toString(), isSelected)
                    }
                },
                monthHeader = { monthState -> CustomMonthHeader(monthState = monthState) },
                weekHeader = { dayOfWeeks -> CustomWeekHeader(daysOfWeek = dayOfWeeks) }
            )
        }
    }
}
@Composable
fun <T : SelectionState> CustomDay(
    state: DayState<T>,
    modifier: Modifier = Modifier,
    isEndOfMonth: Boolean = false,
    onClick: (LocalDate, Boolean) -> Unit = { date, isSelected -> },
) {

    val date = state.date

    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)

    // TODO : HANDLE 31st,1st...
    val isToday = date == LocalDate.now()
    val isAvailableDay = when {
        isEndOfMonth -> {
            val today = LocalDate.now()
            date >= today && date <= today.plusDays(62)
        }

        else -> date >= LocalDate.now() && date <= LocalDate.now().plusDays(62)
    }

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        elevation = 0.dp,
        shape = RectangleShape,
        backgroundColor = if (isSelected) HealthCareTheme.colors.darkBlue else HealthCareTheme.colors.white
    ) {
        Box(
            modifier = Modifier.noRippleClickable {
                if (isAvailableDay) {
                    onClick(date, isSelected)
                    selectionState.onDateSelected(date)
                }
            },
            contentAlignment = Alignment.Center,
        ) {
            if (isAvailableDay && (date.month == LocalDate.now().month || date.month == LocalDate.now().month.plus(
                    1
                ))
            )
                Text(
                    text = date.dayOfMonth.toString(),
                    style = HealthCareTheme.typography.metropolisRegular14,
                    color = if (isSelected) HealthCareTheme.colors.white else HealthCareTheme.colors.darkBlue
                )
            else androidx.compose.material.Text(
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
            androidx.compose.material.Text(
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