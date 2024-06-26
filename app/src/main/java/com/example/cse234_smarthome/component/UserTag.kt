package com.example.cse234_smarthome.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.cse234_smarthome.ui.theme.primaryBlueGray
import com.example.cse234_smarthome.ui.theme.primaryText


@Composable
fun UserTag(
    imageRes: Painter,
    userName: String,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
            ) {
                Image(
                    painter = imageRes,
                    contentDescription = "$userName's picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .clickable(onClick = { showDialog = false })
                )
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(primaryBlueGray)
            .padding(horizontal = 15.dp, vertical = 8.dp) // Daha ince mavi kutu için padding'i değiştirdik
    ) {
        Image(
            painter = imageRes,
            contentDescription = "$userName's picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp) // Resmi büyüttük
                .clip(CircleShape)
                .clickable(onClick = { showDialog = true })
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = userName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = primaryText
        )
    }
}