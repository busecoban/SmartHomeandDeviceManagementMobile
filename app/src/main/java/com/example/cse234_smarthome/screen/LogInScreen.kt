package com.example.cse234_smarthomen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import android.util.Log
import com.example.cse234_smarthome.component.BGImage
import com.example.cse234_smarthome.component.ButtonComponent
import com.example.cse234_smarthome.component.CheckBoxComponent
import com.example.cse234_smarthome.component.LogInTextField
import com.example.cse234_smarthome.component.PasswordTextField
import com.example.cse234_smarthome.data.Routes
import com.example.cse234_smarthome.data.User
import com.example.cse234_smarthome.data.UserManager
import com.example.cse234_smarthome.data.remote.AuthFunction
import com.example.cse234_smarthome.data.remote.dto.AuthRequest

@Composable
fun LogInScreen(navController: NavController) {
    val rememberMeChecked = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val signUpStatus = remember { mutableStateOf("") }
    val client1 = AuthFunction.create()

    Box(modifier = Modifier.fillMaxSize()) {
        BGImage()
        Box(
            modifier = Modifier
                .size(height = 400.dp, width = 300.dp)
                .background(color = Color.Gray.copy(alpha = 0.5f))
                .align(Alignment.Center),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Log In",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                LogInTextField(labelValue = "Email", leadIcon = Icons.Filled.Person) {
                    email.value = it
                }
                PasswordTextField(labelValue = "Password", leadIcon = Icons.Filled.Key) {
                    password.value = it
                }
                CheckBoxComponent(textValue = "Remember Me", checkedState = rememberMeChecked)
                ButtonComponent(
                    textValue = "LOG IN",
                    onClick = {
                        coroutineScope.launch {
                            try {
                                val authRequest = AuthRequest(
                                    Email = email.value,
                                    Password = password.value
                                )
                                val response = client1.authenticate(authRequest)
                                if (response != null) {
                                    if (response.succeeded) {
                                        val user = User(
                                            Id = response.data.id ,
                                            Name = response.data.userName ,
                                            Email = response.data.email ,
                                        )
                                        UserManager.loginUser(user)
                                        signUpStatus.value = UserManager.getCurrentUser()?.Id ?: "Login failed"
                                        navController.navigate(Routes.homeAutomationApp)
                                    } else {
                                        signUpStatus.value = "Login failed: ${response.message}"
                                    }
                                } else {
                                    signUpStatus.value = "Login failed: response null"
                                }
                            } catch (e: Exception) {
                                Log.e("LogInScreen", "Error during login", e)
                                signUpStatus.value = "Error during login: ${e.message}"
                            }
                        }
                    }
                )
                TextButton(onClick = {
                    navController.navigate(Routes.signInScreen)
                }) {
                    Text(
                        text = "Sign Up",
                        color = Color.White,
                        fontSize = 15.sp,
                        textDecoration = TextDecoration.Underline
                    )
                }
                Text(
                    text = signUpStatus.value,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
