package com.example.cse234_smarthome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cse234_smarthome.ui.theme.HomeAutomationTheme
import com.example.cse234_smarthome.ui.theme.primaryBackground

@Composable
fun LightScreen(label: String?, navController: NavController) {
    Column(
        modifier = Modifier
            .background(primaryBackground)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(onClick = {navController.popBackStack()},
                modifier = Modifier.padding(8.dp)) {
                Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "$label",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        SwitchWithLabel(color = Color.White, label = "OFF")
        SwitchWithLabel(color = Color.Yellow, label = "OFF")
        SwitchWithLabel(color = Color.Blue, label = "OFF")
        SwitchWithLabel(color = Color.Magenta, label = "OFF")
        SwitchWithLabel(color = Color.Red, label = "OFF")
    }
}

@Composable
fun SwitchWithLabel(color: Color, label: String) {
    var isOn by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        Switch(
            checked = isOn,
            onCheckedChange = { isOn = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = color,
                uncheckedThumbColor = color.copy(alpha = 0.5f),
                checkedTrackColor = color.copy(alpha = 0.5f),
                uncheckedTrackColor = color.copy(alpha = 0.5f)
            )
        )
        Text(text = if (isOn) "ON" else label)
    }
}

@Preview(showBackground = true)
@Composable
fun LightScreenPreview() {
    HomeAutomationTheme {
        LightScreen(label = "Piranha Smart Bulb", rememberNavController())
       }
}