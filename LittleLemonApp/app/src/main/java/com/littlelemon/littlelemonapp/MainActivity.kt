package com.littlelemon.littlelemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.littlelemon.littlelemonapp.ui.theme.LittleLemonAppTheme
import com.littlelemon.littlelemonapp.ui.theme.MyNavigationComposable
import com.littlelemon.littlelemonapp.ui.theme.Profile


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    MyNavigationComposable(
                        context = applicationContext,
                        navController = navController)
                }

            }
            com.littlelemon.littlelemonapp.ui.theme.Onboarding


        }

    }
}


