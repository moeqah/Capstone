package com.littlelemon.littlelemonapp

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemonapp.ui.theme.Home
import com.littlelemon.littlelemonapp.ui.theme.Onboarding


@Composable
fun OnBoarding(context: Context ,NavHostController: NavHostController) {
    var isvalid: Boolean
    var isFirstNameValid by remember { mutableStateOf(true) }
    var isLastNameValid by remember { mutableStateOf(true) }
    var isEmailValid by remember { mutableStateOf(true) }
    val sharedPreferences = context.getSharedPreferences("order_preferences", ComponentActivity.MODE_PRIVATE)


    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little lemon logo",
                modifier = Modifier
                    .fillMaxWidth(.50f)
                    .align(Alignment.Center)
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .background(Color(0xFF495E57))
        ) {
            Text(
                text = "Let's get to know you",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,

                )
            )
        }
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Personal information",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 40.dp, bottom = 40.dp),

                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,

                ),

                )
            var firstName by remember { mutableStateOf("") }

            TextField(
                value = firstName,
                onValueChange = { newFirstName
                    ->
                    firstName = newFirstName
                    isFirstNameValid = firstName.isNotBlank()
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (isFirstNameValid) Color.Transparent else Color.Red,
                    unfocusedIndicatorColor = if (isFirstNameValid) Color.Transparent else Color.Red
                ),
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                ,
                label = { Text("First name") },

                )

            var lastName by remember { mutableStateOf("") }

            TextField(
                value = lastName,
                onValueChange = { newlastName
                    ->
                    lastName = newlastName
                    isLastNameValid = lastName.isNotBlank()
                },
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 50.dp, end = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                label = { Text("Last name") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (isLastNameValid) Color.Transparent else Color.Red,
                    unfocusedIndicatorColor = if (isLastNameValid) Color.Transparent else Color.Red
                )
            )

            var email by remember { mutableStateOf("") }

            TextField(
                value = email,
                onValueChange = { newEmail
                    ->
                    email = newEmail
                    isEmailValid = email.isNotBlank() && email.contains("@")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 50.dp, end = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                label = { Text("Email") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (isEmailValid) Color.Transparent else Color.Red,
                    unfocusedIndicatorColor = if (isEmailValid) Color.Transparent else Color.Red
                )
            )
            Button(
                onClick = {
                    isvalid = when {
                        firstName.isBlank() -> false
                        lastName.isBlank() -> false
                        email.isBlank() -> false
                        else -> true
                    }
                    if (isvalid) {
                        NavHostController.navigate(Home.route) {
                            popUpTo(Onboarding.route) { inclusive = true }
                            launchSingleTop = true
                        }
                        sharedPreferences.edit()
                            .putString("firstName", firstName)
                            .putString("lastName", lastName)
                            .putString("email", email)
                            .putBoolean("userRegistered", true)
                            .apply()

                        Toast.makeText(
                            context, "Registration successful.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context, "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        isvalid = false
                    }


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 135.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF4CE14)),
                colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14)),

                ) {
                Text(
                    text = "Register",
                    color = Color(0xFF333333),
                    fontSize = 18.sp,

                )
            }
        }
    }
}