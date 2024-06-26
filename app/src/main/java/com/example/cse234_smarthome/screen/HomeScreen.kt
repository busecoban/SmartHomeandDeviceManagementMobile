package com.example.cse234_smarthome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.component.DeviceSection
import com.example.cse234_smarthome.component.HomeDropdownMenu
import com.example.cse234_smarthome.data.Device

val winterHomeDevices = listOf(
    Device(id = 1, name = "Living Room Door", type = "Door", status = true, roomId = 1),
    Device(id = 2, name = "Bathroom Door", type = "Door", status = false, roomId = 1),
    Device(id = 3, name = "Garage Door", type = "Door", status = true, roomId = 1),
    Device(id = 4, name = "Main Entrance Door", type = "Door", status = true, roomId = 1),
    Device(id = 5, name = "Kitchen Window", type = "Window", status = true, roomId = 1),
    Device(id = 6, name = "Bedroom Window", type = "Window", status = false, roomId = 1),
    Device(id = 7, name = "Living Room Light", type = "Light", status = true, roomId = 1),
    Device(id = 8, name = "Dining Room Light", type = "Light", status = false, roomId = 1)
)

val summerHomeDevices = listOf(
    Device(id = 1, name = "Living Room Door", type = "Door", status = false, roomId = 1),
    Device(id = 2, name = "Bathroom Door", type = "Door", status = true, roomId = 1),
    Device(id = 5, name = "Kitchen Window", type = "Window", status = false, roomId = 1),
    Device(id = 6, name = "Bedroom Window", type = "Window", status = true, roomId = 1),
    Device(id = 7, name = "Living Room Light", type = "Light", status = false, roomId = 1),
    Device(id = 8, name = "Dining Room Light", type = "Light", status = true, roomId = 1)
)

val mainHomeDevices = listOf(
    Device(id = 1, name = "Living Room Door", type = "Door", status = true, roomId = 1),
    Device(id = 2, name = "Bathroom Door", type = "Door", status = true, roomId = 1),
    Device(id = 3, name = "Garage Door", type = "Door", status = false, roomId = 1),
    Device(id = 4, name = "Main Entrance Door", type = "Door", status = false, roomId = 1),
    Device(id = 5, name = "Kitchen Window", type = "Window", status = false, roomId = 1),
    Device(id = 6, name = "Bedroom Window", type = "Window", status = true, roomId = 1),
    Device(id = 7, name = "Living Room Light", type = "Light", status = false, roomId = 1),
    Device(id = 8, name = "Dining Room Light", type = "Light", status = true, roomId = 1),
    Device(id = 9, name = "Hallway Light", type = "Light", status = true, roomId = 1)
)

@Composable
fun HomeScreen(navController: NavController) {
    val homes = listOf("Winter Home", "Summer Home", "Main Home")
    var selectedHome by remember { mutableStateOf(homes[0]) }

    val scrollState = rememberScrollState()

    val backgroundColor = when (selectedHome) {
        "Winter Home" -> Color(0xFFE3F2FD) // Açık Mavi
        "Summer Home" -> Color(0xFFFBE9E7) // Açık Turuncu
        "Main Home" -> Color(0xFFEDE7F6) // Açık Mor
        else -> Color(0xFFF5F5F5) //  Açık Gri
    }

    val devices = when (selectedHome) {
        "Winter Home" -> winterHomeDevices
        "Summer Home" -> summerHomeDevices
        "Main Home" -> mainHomeDevices
        else -> emptyList()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .verticalScroll(scrollState)
    ) {
        HomeDropdownMenu(
            homes = homes,
            selectedHome = selectedHome,
            onHomeSelected = { selectedHome = it }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Welcome to",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F),
                textAlign = TextAlign.Center
            )
            Text(
                text = selectedHome,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF37474F),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        DeviceSection(
            sectionTitle = "Doors",
            devices = devices.filter { it.type == "Door" },
            navController = navController
        )

        DeviceSection(
            sectionTitle = "Windows",
            devices = devices.filter { it.type == "Window" },
            navController = navController
        )

        DeviceSection(
            sectionTitle = "Lights",
            devices = devices.filter { it.type == "Light" },
            navController = navController
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}
