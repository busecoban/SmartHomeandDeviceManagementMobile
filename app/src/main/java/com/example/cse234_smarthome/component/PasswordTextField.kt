package com.example.cse234_smarthome.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordTextField(labelValue: String, leadIcon: ImageVector?, onValueChanged: (String) -> Unit) {
    val text = remember {
        mutableStateOf("")
    }
    val visible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onValueChanged(it)
        },
        label = { Text(labelValue, color = Color.White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            focusedLeadingIconColor = Color.White,
        ),
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (visible.value) {
                IconButton(onClick = { visible.value = !visible.value }) {
                    Icon(
                        Icons.Filled.VisibilityOff,
                        contentDescription = "Hide Password",
                        tint = Color.White
                    )
                }
            } else {
                IconButton(onClick = { visible.value = !visible.value }) {
                    Icon(
                        Icons.Filled.Visibility,
                        contentDescription = "Show Password",
                        tint = Color.White
                    )
                }
            }
        },
        visualTransformation = if (visible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        leadingIcon = {
            leadIcon?.let {
                Icon(
                    leadIcon,
                    contentDescription = "Icon",
                    tint = Color.White
                )
            }
        }
    )
}