package com.example.project_1.Presentation.Screen.TeacherScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun Login(viewModel: Project1ViewModel = hiltViewModel(), navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current


    val state = viewModel.loginScreenState.collectAsState()
    if (state.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.value.error.toString().isNullOrBlank()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.value.error.toString())
        }
    } else if (state.value.userData != null) {
        navController.navigate(Routes.TeacherHomeScreen)

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()  // This will make the Column take up the whole screen
                .background(Color.LightGray)  // Set background color of the container
                .background(
                    Color(0xFFFCF7D3)
                )
        ) {
            Text(
                text = "Login", style = TextStyle(
                    fontSize = 80.sp, fontWeight = FontWeight.Bold, color = Color(0xFF020933)
                ), modifier = Modifier.padding(top = 170.dp, start = 15.dp)
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                placeholder = { Text("Enter your email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 15.dp)

            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock, contentDescription = null
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp)

            )

            Button(
                onClick = {
                    if (
                        email.value.isNotBlank() && password.value.isNotBlank()
                    ) {
                        if (email.value.endsWith("ggits.net")) {
                            val userData = UserData(
                                firstName = "",
                                lastName = "",
                                email = email.value,
                                password = password.value,
                                phoneNumber = ""
                            )
                            viewModel.login(userData)
                        } else {
                            Toast.makeText(
                                context,
                                "Please use a valid email address ending with 'ggits.net'",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()


                    }
                },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF020933)
                )
            ) {
                Text(text = "Sign in")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Create account?")
                TextButton(onClick = {
                    navController.navigate(Routes.SingUpScreen)
                }) {
                    Text("Sign Up", color = Color.Blue)
                }
            }

        }
    }
}
