package com.example.healthcareapp.presentation.results

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.core.components.FullScreenLoader
import com.example.healthcareapp.core.utils.observeWithLifecycle
import com.example.healthcareapp.ui.theme.HealthCareTheme

@Composable
fun ResultsScreen(
    viewModel : ResultsViewModel,
    showSnackBar : (String) -> Unit
) {
    val state = viewModel.state
    viewModel.snackBarChannel.observeWithLifecycle{
        showSnackBar(it)
    }
    FullScreenLoader(shouldShowLoader = state.shouldShowLoader)

    Column(modifier = Modifier.fillMaxSize()){
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Nalazi",
                style = HealthCareTheme.typography.metropolisBold20,
                color = HealthCareTheme.colors.darkBlue
            )
        }
        Divider(color = HealthCareTheme.colors.secondaryColor)

        LazyColumn( modifier = Modifier
            .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(state.results){
                ResultItem(
                    date = it.date,
                    title = it.title,
                    lab = it.lab
                ){
                    viewModel.onEvent(ResultsEvent.OnResultClick(it.id))
                }
            }
        }
    }
}