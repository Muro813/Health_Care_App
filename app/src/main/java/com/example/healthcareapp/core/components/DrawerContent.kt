package com.example.healthcareapp.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.healthcareapp.core.utils.noRippleClickable
import com.example.healthcareapp.domain.model.DrawerNavItem

@Composable
fun DrawerContent(
    items : List<DrawerNavItem>,
    drawerState : Boolean,
    onItemClick : (String) -> Unit
) {

    if(drawerState){
        Column {
            items.forEach {
                Row(modifier = Modifier.fillMaxWidth(0.7f).noRippleClickable { onItemClick(it.route) }) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null
                    )
                    Text(text = it.name)
                }
                Divider()
            }
        }
    }

}