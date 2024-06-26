package com.example.cse234_smarthome.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.cse234_smarthome.R

@Composable
fun BGImage() {
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Log In Screen Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}