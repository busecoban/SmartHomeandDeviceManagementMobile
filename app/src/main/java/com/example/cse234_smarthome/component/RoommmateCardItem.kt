package com.example.cse234_smarthome.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cse234_smarthome.ui.theme.primaryBorder
import com.example.cse234_smarthome.ui.theme.primaryText

@Composable
fun RoommateCardItem(userName: String) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        border = BorderStroke(1.dp, color = primaryBorder)
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = userName,
                style = androidx.compose.material.MaterialTheme.typography.body1.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                    color = primaryText
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
