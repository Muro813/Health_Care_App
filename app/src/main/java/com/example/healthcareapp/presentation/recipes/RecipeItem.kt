package com.example.healthcareapp.presentation.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.domain.model.Medicine
import com.example.healthcareapp.ui.theme.HealthCareTheme

@Composable
fun RecipeItem(
    role : Int,
    medicine : MutableList<Medicine>,
    patientName : String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp,HealthCareTheme.colors.gray, RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        if(role == 0){
            Text(text = "$patientName:", style = HealthCareTheme.typography.metropolisBold18, color = HealthCareTheme.colors.darkBlue)
        }
        else {
            Text(text = "Trenutna terapija:", style = HealthCareTheme.typography.metropolisBold18, color = HealthCareTheme.colors.darkBlue)
        }
        medicine.forEachIndexed { index, it ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(if(index % 2 == 0) HealthCareTheme.colors.white else HealthCareTheme.colors.darkerGray),
                    verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Text(
                            text = it.name,
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
                            text = "Ostalo još ${it.timeLeft}",
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
                            text = it.dosage,
                            style = HealthCareTheme.typography.metropolisRegular16,
                            color = HealthCareTheme.colors.darkBlue,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
        }
    }
}