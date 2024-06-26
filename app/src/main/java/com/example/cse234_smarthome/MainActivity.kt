package com.example.cse234_smarthome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cse234_smarthome.app.HomeAutomationApp
import com.example.cse234_smarthome.data.Routes
import com.example.cse234_smarthome.screen.SignUpScreen
import com.example.cse234_smarthomen.screen.LogInScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "LogInScreen") {
                composable(Routes.logInScreen){
                    LogInScreen(navController)
                }
                composable(Routes.signInScreen){
                    SignUpScreen(navController)
                }
                composable(Routes.homeAutomationApp){
                   HomeAutomationApp()
                }
            }
        }
    }
}