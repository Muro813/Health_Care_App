package com.example.healthcareapp.presentation.results

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.core.utils.noRippleClickable
import com.example.healthcareapp.ui.theme.HealthCareTheme

@Composable
fun ResultItem(
    date : String,
    title : String,
    lab : String,
    onItemClick : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .border(1.dp, HealthCareTheme.colors.blue, RoundedCornerShape(12.dp))
            .noRippleClickable {
                onItemClick()
            }
            .padding(10.dp),
       verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = title,
            style = HealthCareTheme.typography.metropolisBold16
        )
        Text(
            text = "$lab · $date",
            style = HealthCareTheme.typography.metropolisRegular14,
            color = HealthCareTheme.colors.darkBlue.copy(alpha = 0.5f)
        )
    }
}