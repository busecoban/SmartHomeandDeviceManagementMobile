package com.example.cse234_smarthome.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cse234_smarthome.data.Routes
import com.example.cse234_smarthome.ui.theme.primaryBackground
import com.example.cse234_smarthome.ui.theme.primaryBorder
import com.example.cse234_smarthome.ui.theme.primaryText

@Composable
fun RoomCard(
    name: String?,
    navController: NavController,
    onEditClick: (String) -> Unit
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf(name ?: "") }

    if (showEditDialog) {
        AddDialog(
            description = "Change Room Name",
            label1 = "New Room Name",
            label2 = "",
            onDismiss = { showEditDialog = false },
            onSave = { updatedName ->
                onEditClick(updatedName)
                newName = updatedName
                showEditDialog = false
            }
        )
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = primaryBackground),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        border = BorderStroke(1.dp, primaryBorder)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit Room Name",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { showEditDialog = true },
                tint = primaryText
            )

            Text(
                text = newName,
                style = MaterialTheme.typography.bodyLarge,
                color = primaryText,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(200.dp))
            IconButton(onClick = {
                navController.navigate(Routes.roomScreen +"/$name")
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "",
                    tint = primaryText)
            }
           }
       }
}

@Preview
@Composable
private fun ff() {
    RoomCard(name = "fgdfhr", navController = rememberNavController()) {

    }
    
}