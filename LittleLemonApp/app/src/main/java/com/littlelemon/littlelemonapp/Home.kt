package com.littlelemon.littlelemonapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemonapp.ui.theme.LittleLemonColor

@Composable
fun Home(navController : NavHostController, readPreferences : () -> Map<String, String>,  databaseMenuItems : List<MenuItemRoom>) {
    var menuSearch by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var filter by remember {
        mutableStateOf("all")
    }

    Column() {
        Box(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription="Little Lemon Logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .padding(8.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(contentColor = LittleLemonColor.darkGrayGreen),
                    onClick = { navController.navigate(Profile.route) }
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Profile",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply{setToScale(0.5f,0.5f,0.5f,1f)}),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Little Lemon",
                    style = MaterialTheme.typography.h1,
                    color = LittleLemonColor.yellow
                )
                Text(
                    text = "Chicago",
                    style = MaterialTheme.typography.h2,
                    color = LittleLemonColor.white
                )
                Text(
                    text = "We are a family owned mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    color = LittleLemonColor.white
                )
                TextField(
                    value = menuSearch,
                    onValueChange = {menuSearch = it},
                    leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")},
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = LittleLemonColor.white,
                        cursorColor = LittleLemonColor.yellow,
                        focusedIndicatorColor = LittleLemonColor.yellow,
                        unfocusedIndicatorColor = LittleLemonColor.darkGrayGreen,
                        focusedLabelColor = LittleLemonColor.darkGrayGreen,
                        unfocusedLabelColor = LittleLemonColor.darkGrayGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
            }
        }

        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "all" }
            ) {
                Text(
                    text = "All",
                    style = MaterialTheme.typography.h3
                )
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "starters" }
            ) {
                Text(
                    text = "Starters",
                    style = MaterialTheme.typography.h3
                )
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "mains" }
            ) {
                Text(
                    text = "Mains",
                    style = MaterialTheme.typography.h3
                )
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "desserts" }
            ) {
                Text(
                    text = "Desserts",
                    style = MaterialTheme.typography.h3
                )
            }
        }
        if (filter == "all") {
            MenuItems(databaseMenuItems.filter { it.title.lowercase().contains(menuSearch.text.lowercase()) })
        } else {
            MenuItems(databaseMenuItems
                .filter { it.title.lowercase().contains(menuSearch.text.lowercase()) }
                .filter { it.category == filter }
            )
        }

    }
}