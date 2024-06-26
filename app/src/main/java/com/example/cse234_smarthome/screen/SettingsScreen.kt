package com.example.cse234_smarthome.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.component.AddDialog2
import com.example.cse234_smarthome.data.Routes
import com.example.cse234_smarthome.ui.theme.primaryBackground
import com.example.cse234_smarthome.ui.theme.primaryBorder
import com.example.cse234_smarthome.ui.theme.primaryText

@Composable
fun SettingsScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogType by remember { mutableStateOf(DialogType.NAME) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Settings",
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SettingItem(
            text = "Change name surname",
            onClick = {
                dialogTitle = "Change Name Surname"
                dialogType = DialogType.NAME
                showDialog = true
            }
        )

        SettingItem(
            text = "Change email",
            onClick = {
                dialogTitle = "Change Email"
                dialogType = DialogType.EMAIL
                showDialog = true
            }
        )

        SettingItem(
            text = "Change password",
            onClick = {
                dialogTitle = "Change Password"
                dialogType = DialogType.PASSWORD
                showDialog = true
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {  navController.navigate(Routes.logInScreen)},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            shape = CircleShape,
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
        ) {
            Text(text = "Log Out", color = Color.White)
        }
    }

    if (showDialog) {
        AddDialog2(
            description = dialogTitle,
            dialogType = dialogType,
            onDismiss = { showDialog = false },
            onSave = { newValue, password ->
                // Implement save logic, including password validation here
            }
        )
    }
}

@Composable
fun SettingItem(text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .heightIn(max = 100.dp)
            .fillMaxWidth()
            .padding(15.dp),
        border = BorderStroke(1.dp, color = primaryBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = primaryText,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    tint = primaryText
                )
            }
        }
    }
}

enum class DialogType {
    NAME, EMAIL, PASSWORD
}
