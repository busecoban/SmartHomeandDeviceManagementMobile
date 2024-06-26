package com.example.cse234_smarthome.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.data.Device
import com.example.cse234_smarthome.data.Routes
import com.example.cse234_smarthome.R
import com.example.cse234_smarthome.ui.theme.primaryText

@Composable
fun DeviceCard(
    device: Device,
    navController: NavController
) {
    val imageResource = when (device.type) {
        "Door" -> if (device.status) R.drawable.opendoor else R.drawable.door
        "Window" -> if (device.status) R.drawable.openvindow else R.drawable.closewindowson
        "Light" -> if (device.status) R.drawable.bublopen else R.drawable.bulbclose
        else -> R.drawable.home // Varsayılan bir görsel (tanımsız durumlar için)
    }

    val route = when (device.type) {
        "Door" -> Routes.doorSensorScreen
        "Window" -> Routes.windowSensorScreen
        "Light" -> Routes.lightScreen
        else -> Routes.homeScreen
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {
                navController.navigate("$route/${device.name}")
            }),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = device.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = if (device.status) "open" else "close",
                    fontSize = 14.sp,
                    color = if (device.status) Color.Green else Color.Red
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                navController.navigate("$route/${device.name}")
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "",
                    tint = primaryText
                )
            }
        }
    }
}
