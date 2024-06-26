package com.example.cse234_smarthome.app

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cse234_smarthome.component.BottomBar
import com.example.cse234_smarthome.component.BottomNavGraph


@Composable
fun HomeAutomationApp() {
    val navController = rememberNavController()
    Scaffold (bottomBar = { BottomBar(navController = navController)}){it
        Column(modifier = Modifier.fillMaxSize()) {
            BottomNavGraph(navController = navController)
        }
    }
}

@Preview
@Composable
fun HomeAutPreview(){
    HomeAutomationApp()
}