package com.littlelemon.littlelemonapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemonapp.ui.theme.LittleLemonColor


@Composable
fun OnBoarding(navController : NavHostController, savePreferencesFun : (firstName : String, lastName : String, email : String) -> Unit) {
    var firstName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var lastName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    val inputFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = LittleLemonColor.white,
        cursorColor = LittleLemonColor.yellow,
        focusedIndicatorColor = LittleLemonColor.yellow,
        unfocusedIndicatorColor = LittleLemonColor.darkGrayGreen,
        focusedLabelColor = LittleLemonColor.darkGrayGreen,
        unfocusedLabelColor = LittleLemonColor.darkGrayGreen
    )

    fun tryRegister() {
        if (firstName.text == "" || lastName.text == "" || email.text == "") {
            errorMessage = "Registration unsuccessful. Please fill in all fields"
        } else {
            savePreferencesFun(firstName.text, lastName.text, email.text)
            navController.navigate(Home.route)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
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
        Text(
            text ="Let's get to know you",
            textAlign = TextAlign.Center,
            color = LittleLemonColor.white,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor.lightGrayGreen)
                .padding(top = 24.dp, bottom = 24.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Personal Information",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                value = firstName,
                onValueChange = { firstName = it},
                label = {Text("First Name")},
                colors = inputFieldColors,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                value = lastName,
                onValueChange = { lastName = it},
                label = {Text("Last Name")},
                colors = inputFieldColors,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                value = email,
                onValueChange = { email = it},
                label = {Text("Email")},
                colors = inputFieldColors,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column() {
                Button(
                    onClick = { tryRegister() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.button
                    )
                }
                Text(
                    text = errorMessage,
                    color = LittleLemonColor.error,
                    fontSize = 12.sp
                )
            }
        }
    }

}