package com.littlelemon.littlelemonapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemonapp.R
import java.lang.reflect.Modifier

@Composable
fun Home(navController: NavHostController){

    Row(
        androidx.compose.ui.Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {

        Spacer(modifier = androidx.compose.ui.Modifier.width(50.dp))
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Little Lemon Logo",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth(0.65f)
                .size(40.dp)

        )

        Box(modifier = androidx.compose.ui.Modifier
            .size(50.dp)
            .clickable { navController.navigate(Profile.route) }){
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .padding(vertical = 2.dp)
            )

        }
    }
}
