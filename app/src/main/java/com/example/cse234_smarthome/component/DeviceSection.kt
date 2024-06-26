package com.example.cse234_smarthome.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.data.Device

@Composable
fun DeviceSection(
    sectionTitle: String,
    devices: List<Device>,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFFB0BEC5), shape = MaterialTheme.shapes.medium) // Arka plan ve yuvarlak köşeler
            .padding(16.dp) // İç kenar boşluğu
    ) {
        Column {
            Text(
                text = sectionTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F)
            )

            Spacer(modifier = Modifier.height(8.dp))

            devices.forEach { device ->
                DeviceCard(device = device, navController = navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
