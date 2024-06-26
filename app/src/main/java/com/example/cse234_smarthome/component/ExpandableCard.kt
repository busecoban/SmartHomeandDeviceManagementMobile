package com.example.cse234_smarthome.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.R
import com.example.cse234_smarthome.data.Device
import com.example.cse234_smarthome.data.Room
import com.example.cse234_smarthome.data.User
import com.example.cse234_smarthome.ui.theme.primaryBackground
import com.example.cse234_smarthome.ui.theme.primaryBorder
import com.example.cse234_smarthome.ui.theme.primaryText

@Composable
fun ExpandableCard(
    title: String,
    rooms: List<Room>?,
    roommates: List<User>?,
    devices: List<Device>,
    navController: NavController,
    onEditClick: (String) -> Unit,
    onAddRoomClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var roommatesExpanded by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf(title) }

    if (showEditDialog) {
        AddDialog(
            description = "Change House Name",
            label1 = "New House Name",
            label2 = "",
            onDismiss = { showEditDialog = false },
            onSave = { updatedName ->
                onEditClick(updatedName)
                showEditDialog = false
            }
        )
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = primaryBackground),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { expanded = !expanded },
        border = BorderStroke(1.dp, color = primaryBorder)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Spacer(modifier = Modifier.size(5.dp))
                Icon(
                    painter = painterResource(id = R.drawable.home120),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.size(5.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(start = 5.dp),
                        fontSize = 20.sp,
                        color = primaryText
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Icon",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable { showEditDialog = true },
                    tint = primaryText
                )
                Icon(
                    imageVector = if (expanded) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Drop The Content",
                    modifier = Modifier.padding(10.dp),
                    tint = primaryText
                )
            }
            if (expanded) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Rooms",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Room",
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clickable { onAddRoomClick() },
                            tint = primaryText
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    rooms?.forEach { room ->
                        RoomCard(
                            name = room.Name,
                            navController = navController,
                            onEditClick = {}
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { roommatesExpanded = !roommatesExpanded }
                    ) {
                        Text(
                            text = "Roommates",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = if (roommatesExpanded) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowRight,
                            contentDescription = "Expand/Collapse Roommates",
                            tint = Color.Black
                        )
                    }
                    if (roommatesExpanded) {
                        RoommateCard(roommates = roommates?.distinctBy { it.Id })
                    }
                }
            }
           }
      }
}