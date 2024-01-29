package com.example.healthcareapp.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthcareapp.core.utils.noRippleClickable
import com.example.healthcareapp.domain.model.DrawerNavItem
import com.example.healthcareapp.ui.theme.HealthCareTheme

@Composable
fun DrawerContent(
    items : List<DrawerNavItem>,
    drawerState : Boolean,
    onItemClick : (String) -> Unit
) {

    if(drawerState){
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            items.forEach {
                Row(
                    modifier = Modifier
                    .noRippleClickable { onItemClick(it.route) },
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null
                    )
                    Text(text = it.name, style = HealthCareTheme.typography.metropolisMedium16)
                }
                Divider()
            }
        }
    }
}