package com.example.healthcareapp.presentation.result_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.R
import com.example.healthcareapp.core.components.FullScreenLoader
import com.example.healthcareapp.core.utils.observeWithLifecycle
import com.example.healthcareapp.ui.theme.HealthCareTheme

@Composable
fun ResultInfoScreen(
    viewModel: ResultInfoViewModel,
    resultId : Int,
    showSnackBar : (String) -> Unit
) {
    val state = viewModel.state
    viewModel.snackBarChannel.observeWithLifecycle{
        showSnackBar(it)
    }
    FullScreenLoader(shouldShowLoader = state.shouldShowLoader)

    LaunchedEffect(key1 = resultId){
        viewModel.onEvent(ResultInfoEvent.GetData(resultId))
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item{
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp))
            {
                IconButton(onClick = { viewModel.onEvent(ResultInfoEvent.OnBackClick) }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null )
                }
                Text(text = "BACK", style = HealthCareTheme.typography.metropolisBold20)
            }
            Divider()
        }
        item{
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(HealthCareTheme.colors.white),
                verticalAlignment = Alignment.CenterVertically) {
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(
                        text = "Analiza",
                        style = HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically){
                    Divider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Text(
                        text = "Rezultati",
                        style = HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically){
                    Divider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Text(
                        text = "Referente vrijednosti",
                        style = HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically){
                    Divider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Text(
                        text = "Jedinica",
                        style = HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        itemsIndexed(state.results.analysis){ index, _ ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(if (index % 2 == 0) HealthCareTheme.colors.gray else HealthCareTheme.colors.white),
                verticalAlignment = Alignment.CenterVertically) {
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(
                        text = state.results.analysis[index],
                        style = HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically){
                    Divider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Text(
                        text = state.results.results[index],
                        style = if(state.results.results[index].contains("H") || state.results.results[index].contains("L"))HealthCareTheme.typography.metropolisBold16 else HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically){
                    Divider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Text(
                        text = state.results.referenceValues[index],
                        style = HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                        )
                }
                Row(modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically){
                    Divider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Text(
                        text = state.results.units[index],
                        style = HealthCareTheme.typography.metropolisRegular16,
                        color = HealthCareTheme.colors.darkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                        )
                }
            }
        }
        item{
            Divider()
        }
    }

}