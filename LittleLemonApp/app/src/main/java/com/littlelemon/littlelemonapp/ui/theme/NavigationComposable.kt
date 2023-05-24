package com.littlelemon.littlelemonapp.ui.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.littlelemon.littlelemonapp.OnBoarding

@Composable
fun MyNavigationComposable(context: Context ,navController: NavHostController) {
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var startDestination = Onboarding.route

    if (sharedPreferences.getBoolean("userRegistered", false)) {
        startDestination = Home.route
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            OnBoarding(context, navController)
        }

        composable(Home.route) {
            Home(navController = navController)
        }

        composable(Profile.route) {
            Profile(context ,navController)
        }

    }

}