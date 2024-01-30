package com.example.healthcareapp.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthcareapp.R
import com.example.healthcareapp.core.utils.noRippleClickable
import com.example.healthcareapp.domain.model.DrawerNavItem
import com.example.healthcareapp.ui.theme.HealthCareTheme

@Composable
fun DrawerContent(
    items : List<DrawerNavItem>,
    drawerState : Boolean,
    onItemClick : (String) -> Unit,
    onLogOutClick : () -> Unit
) {

    if(drawerState){
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            items.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                    .noRippleClickable { onItemClick(it.route) },
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = it.name, style = HealthCareTheme.typography.metropolisBold20)
                }
                Divider()
            }
            Box(modifier = Modifier.fillMaxSize()){
                Row(
                    modifier = Modifier.align(
                        Alignment.BottomStart
                    ).noRippleClickable {
                        onLogOutClick()
                    },
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Odjava",
                        style = HealthCareTheme.typography.metropolisBold20,
                    )
                }
            }
        }
    }
}