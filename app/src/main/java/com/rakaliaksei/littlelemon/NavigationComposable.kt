package com.rakaliaksei.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rakaliaksei.littlelemon.screens.*

@Composable
fun Navigation(sharedPref: SharedPreferences) {
    val navController = rememberNavController()
    val loggedIn = sharedPref.getBoolean("LoggedIn", false)

    NavHost(
        navController = navController,
        startDestination = if (loggedIn) Home.route else Onboarding.route
    ) {
        composable(Onboarding.route) {
            Onboarding(navController, sharedPref)
        }
        composable(Home.route) {
            Home(navController)
        }
        composable(Profile.route) {
            Profile(navController, sharedPref)
        }
    }
}