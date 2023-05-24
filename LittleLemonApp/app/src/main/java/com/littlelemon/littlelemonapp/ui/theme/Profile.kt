package com.littlelemon.littlelemonapp.ui.theme

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemonapp.R


@Composable
fun Profile(context: Context, NavHostController: NavHostController) {
    val  sharedPreferences = context.getSharedPreferences("order_preferences", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "")
    val lastName = sharedPreferences.getString("lastName", "")
    val email = sharedPreferences.getString("email", "")
    Column {
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
                )
            )

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile"
            )

            TextField(
                value = firstName.toString(),
                label = { Text("First Name") },
                onValueChange = {  },
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp, end = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = lastName.toString(),
                label = { Text("Last Name") },
                onValueChange = {  },
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = email.toString(),
                label = { Text("Email") },
                onValueChange = {  },
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Button(
                onClick = {
                    sharedPreferences.edit()
                        .clear()
                        .apply()

                    NavHostController.navigate(Onboarding.route) {
                        popUpTo(Home.route) { inclusive = true }
                        launchSingleTop = true
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 160.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF4CE14)),
                colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14)),
            ) {
                Text(
                    text = "Logout",
                    color = Color(0xFF333333),
                    fontSize = 18.sp,
                )
            }
        }
    }
}

