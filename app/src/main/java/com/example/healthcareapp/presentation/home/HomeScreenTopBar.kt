package com.example.healthcareapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.healthcareapp.ui.theme.HealthCareTheme

@Composable
fun HomeScreenTopBar(
    name : String,
    image : String,
    speciality : String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(HealthCareTheme.colors.secondaryColor)
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(54.dp)
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = "Dr. $name",
                style = HealthCareTheme.typography.metropolisBold18,
                color = HealthCareTheme.colors.white
            )
            Text(
                text = speciality,
                style = HealthCareTheme.typography.metropolisRegular16,
                color = HealthCareTheme.colors.white
            )
        }
    }

}