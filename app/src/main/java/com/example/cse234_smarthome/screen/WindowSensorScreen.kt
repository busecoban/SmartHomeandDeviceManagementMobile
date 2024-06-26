package com.example.cse234_smarthome.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cse234_smarthome.R
import com.example.cse234_smarthome.ui.theme.primaryBackground
import com.example.cse234_smarthome.ui.theme.primaryBlueGray
import com.example.cse234_smarthome.ui.theme.primaryText

@Composable
fun WindowSensorScreen(label : String?,navController: NavController) {
    val isOpen = remember { mutableStateOf(true) }
    val image = if (isOpen.value) {
        painterResource(id = R.drawable.openwindow)
    } else {
        painterResource(id = R.drawable.window)
    }
    Column (
        modifier = Modifier
            .background(Color(0xFFEDE7F6))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
        Text(text = "$label",
            fontSize =40.sp,
            fontWeight = FontWeight.SemiBold,
            color = primaryText,
            modifier = Modifier
                .padding(40.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(20.dp))
        Image(painter = image,
            contentDescription ="",
            modifier = Modifier
                .size(250.dp)
                .padding(8.dp),
            contentScale = ContentScale.Fit)
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = if(isOpen.value)"$label is open!" else "$label is closed!",
            fontSize =20.sp,
            fontWeight = FontWeight.SemiBold,
            color = primaryText,
            modifier = Modifier
                .padding(40.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = { isOpen.value = !isOpen.value },
            modifier = Modifier.padding(20.dp)
                .size(width = 300.dp,
                    height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryBlueGray
            )) {
            Text(if(isOpen.value)"Close the window" else "Open the window",
                color = primaryText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WindowSensorScreenPreview() {
    val navController = rememberNavController()
    WindowSensorScreen(label = "Living Room Window", navController = navController)
}