package com.example.cse234_smarthome.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cse234_smarthome.data.User
import com.example.cse234_smarthome.ui.theme.primaryBorder
import com.example.cse234_smarthome.ui.theme.primaryText

@Composable
fun RoommateCard(roommates: List<User>?) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        roommates?.distinctBy { it.Id }?.forEach { roommate ->
            RoommateCardItem(userName = roommate.Name)
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { showDialog = true },
            border = BorderStroke(1.dp, color = primaryBorder)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Add Roommate",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                    color = primaryText,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Roommate",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
        }

        if (showDialog) {
            AddDialog(
                description = "Add a New Roommate",
                label1 = "Name",
                label2 = "ID",
                onDismiss = { showDialog = false },
                onSave = { newName ->
                    // Yeni oda arkadaşını ekleme işlemi burada yapılacak
                    // Örneğin, yeni oda arkadaşını `roommates` listesine ekleyebilirsiniz.
                    showDialog = false
                }
            )
        }
    }
}
