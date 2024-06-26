package com.example.cse234_smarthome.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cse234_smarthome.data.Routes
import com.example.cse234_smarthome.screen.DoorSensorScreen
import com.example.cse234_smarthome.screen.HomeScreen
import com.example.cse234_smarthome.screen.LightScreen
import com.example.cse234_smarthome.screen.ProfileScreen
import com.example.cse234_smarthome.screen.SettingsScreen
import com.example.cse234_smarthome.screen.WindowSensorScreen
import com.example.cse234_smarthome.screen.RoomScreen
import com.example.cse234_smarthome.screen.SignUpScreen
import com.example.cse234_smarthomen.screen.LogInScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.homeScreen) {
        composable(route = Routes.homeScreen) {
            HomeScreen(navController = navController)
        }
        composable(route = Routes.profileScreen) {
            ProfileScreen(navController = navController)
        }
        composable(route = Routes.logInScreen) {
            LogInScreen(navController = navController)
        }
        composable(route = Routes.signInScreen) {
           SignUpScreen(navController = navController)
        }
        composable(route = Routes.settingsScreen) {
            SettingsScreen(navController)
        }
        composable(
            route = Routes.doorSensorScreen + "/{label}",
            arguments = listOf(
                navArgument(name = "label") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val label = backStackEntry.arguments?.getString("label")
            if (label != null) {
                DoorSensorScreen(label = label, navController = navController)
            }
        }
        composable(
            route = Routes.windowSensorScreen + "/{label}",
            arguments = listOf(
                navArgument(name = "label") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val label = backStackEntry.arguments?.getString("label")
            if (label != null) {
                WindowSensorScreen(label = label, navController = navController)
            }
        }
        composable(
            route = Routes.lightScreen + "/{label}",
            arguments = listOf(
                navArgument(name = "label") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val label = backStackEntry.arguments?.getString("label")
            if (label != null) {
                LightScreen(label = label,navController)
            }
        }
        composable(
            route = Routes.roomScreen + "/{label}",
            arguments = listOf(
                navArgument(name = "label") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val label = backStackEntry.arguments?.getString("label")
            if (label != null) {
                RoomScreen(label = label, navController = navController)
            }
            }
       }
}