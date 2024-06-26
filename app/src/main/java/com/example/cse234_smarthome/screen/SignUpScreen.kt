package com.example.cse234_smarthome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.data.Routes
import com.example.cse234_smarthome.component.BGImage
import com.example.cse234_smarthome.component.ButtonComponent
import com.example.cse234_smarthome.component.CheckBoxComponent
import com.example.cse234_smarthome.component.LogInTextField
import com.example.cse234_smarthome.component.PasswordTextField
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
        })
    }
    install(Logging) {
        level = LogLevel.BODY
    }

}
@Composable
fun SignUpScreen(navController: NavController) {
    val firstname = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmpassword = remember { mutableStateOf("") }
    val passwordsMatch = remember { mutableStateOf(true) }
    val rememberMeChecked = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val signUpStatus = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        BGImage()
        Box(
            modifier = Modifier
                .size(height = 650.dp, width = 300.dp)
                .background(color = Color.Gray.copy(alpha = 0.5f))
                .align(Alignment.Center),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Sign Up",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                LogInTextField(labelValue = "First Name", leadIcon = null) {
                    firstname.value = it
                }
                LogInTextField(labelValue = "Last Name", leadIcon = null) {
                    lastname.value = it
                }
                LogInTextField(labelValue = "Username", leadIcon = Icons.Filled.Person) {
                    username.value = it
                }
                LogInTextField(labelValue = "Email", leadIcon = Icons.Filled.Email) {
                    email.value = it
                }
                PasswordTextField(labelValue = "Password", leadIcon = Icons.Filled.Key) {
                    password.value = it
                }
                PasswordTextField(labelValue = "Confirm Password", leadIcon = Icons.Filled.Key) {
                    confirmpassword.value = it
                    passwordsMatch.value = password.value == it
                }
                if (!passwordsMatch.value) {
                    Text(
                        text = "Passwords do not match",
                        color = Color.Red
                    )
                }
                CheckBoxComponent(textValue = "Remember Me", checkedState = rememberMeChecked)
                ButtonComponent(textValue = "Sign Up",
                    onClick = {
                        if (passwordsMatch.value) {
                            coroutineScope.launch {
                                try {
                                    val url = "https:///softwarebackenddeployment2.azurewebsites.net/api/Account/register" +
                                            "?FirstName=${firstname.value}&LastName=${lastname.value}&Email=${email.value}&UserName=${username.value}&Password=${password.value}&ConfirmPassword=${confirmpassword.value}"
                                    val response: HttpResponse = client.post(url) {
                                        contentType(ContentType.Application.Json)
                                    }
                                    signUpStatus.value = "Registration successful: ${response.status}"

                                    navController.navigate(Routes.logInScreen)
                                } catch (e: Exception) {
                                    signUpStatus.value = "Error during registration: ${e.message}"
                                }
                            }
                        } else {
                            signUpStatus.value = "Passwords do not match"
                        }

                    })
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account?",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                    TextButton(onClick = { navController.navigate(Routes.logInScreen) }) {
                        Text(
                            text = "Log In",
                            color = Color.White,
                            fontSize = 15.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
                // Display sign-up status
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
