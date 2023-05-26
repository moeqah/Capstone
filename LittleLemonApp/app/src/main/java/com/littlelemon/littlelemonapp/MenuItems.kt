package com.littlelemon.littlelemonapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MenuItems(databaseMenuItems : List<MenuItemRoom>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (item in databaseMenuItems) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.h4
                    )
                    Text(
                        text = "$" + item.price,
                        style = MaterialTheme.typography.body2
                    )

                    Image(
                        painter = rememberAsyncImagePainter(item.imageUrl),
                        contentDescription = "$item.title Image",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .size(60.dp)
                    )
                }

            }
        }
    }
}