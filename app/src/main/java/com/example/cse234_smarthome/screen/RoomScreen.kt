package com.example.cse234_smarthome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.component.DeviceSection
import com.example.cse234_smarthome.data.Device
import com.example.cse234_smarthome.ui.theme.primaryText


@Composable
fun RoomScreen(label : String,navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
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
        Text(text = label,
            modifier = Modifier.padding(15.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = primaryText
        )
        DeviceSection(sectionTitle = "Devices",
            devices = listOf(
                Device(id = 1, name = "Living Room Door", type = "Door", status = true, roomId = 1),
                Device(id = 7, name = "Living Room Light", type = "Light", status = true, roomId = 1),
                Device(id = 7, name = "Living Room Window", type = "Window", status = true, roomId = 1),
            ) ,
            navController = navController)
        }
}