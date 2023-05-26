package com.littlelemon.littlelemonapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemonapp.ui.theme.LittleLemonColor


@Composable
fun Profile(navController: NavHostController,
            readPreferences : () -> Map<String, String>,
            savePreferences : (firstName : String, lastName : String, email : String) -> Unit) {

    val userDetails = readPreferences()

    fun logout() {
        savePreferences("", "", "")
        navController.navigate(Onboarding.route)
    }

    Column() {
        Box(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(contentColor = LittleLemonColor.darkGrayGreen),
                    onClick = { navController.navigate(Home.route) }
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Back",
                        style = MaterialTheme.typography.button
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .padding(8.dp)
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Profile Information",
                style = MaterialTheme.typography.h3
            )

            Column() {
                Text(
                    text = "First Name:",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "${userDetails["firstName"]}"
                )
            }

            Column() {
                Text(
                    text = "Last Name:",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "${userDetails["lastName"]}"
                )
            }

            Column() {
                Text(
                    text = "Email:",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "${userDetails["email"]}"
                )
            }

            Button(
                onClick = { logout() },
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            ) {
                Text(
                    text = "Log Out",
                )
            }
        }
    }
}
