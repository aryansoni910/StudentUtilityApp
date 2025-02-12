package com.example.project_1.Presentation.Screen.TeacherScreen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import com.example.project_1.R
import androidx.compose.ui.unit.sp as sp

@Composable
fun SingUpScreenUi(
    viewModel: Project1ViewModel = hiltViewModel(),
    navController: NavController

) {

//f
    val state = viewModel.SignUpState.collectAsState()
    val context = LocalContext.current

    if (state.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else if (state.value.error.toString().isNullOrBlank()) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = state.value.error.toString())

        }
    } else if (state.value.userdata != null) {
        navController.navigate(Routes.LoginScreen)

    } else {


        var firstName = remember { mutableStateOf("") }
        var lastName = remember { mutableStateOf("") }
        var email = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }
        var confirmPassword = remember { mutableStateOf("") }
        var phoneNumber = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(0xFFFCF7D3)
                )
        )

        {



            Text(
                text = "SignUp",
                style = TextStyle(
                    fontSize = 100.sp, fontWeight = FontWeight.Bold, color = Color(0xFF01051C)
                ), modifier = Modifier.padding(top = 120.dp)
            )

            OutlinedTextField(
                value = firstName.value,
                onValueChange = { firstName.value = it },
                label = { Text("FirstName") },
                placeholder = { Text("Enter your FirstName") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 15.dp)

            )

            OutlinedTextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                label = { Text("lastName") },
                placeholder = { Text("Enter your LastName") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)

            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                placeholder = { Text("Enter your Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)

            )


            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                placeholder = { Text("Enter your Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)

            )



            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                label = { Text("confirmPassword") },
                placeholder = { Text("Enter your ConfirmPassword") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)

            )

            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text("PhoneNumber") },
                placeholder = { Text("Enter your PhoneNumber") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)

            )


            Button(
                onClick = {


                    if (
                        firstName.value.isNotBlank() && lastName.value.isNotBlank() && email.value.isNotBlank()
                        && password.value.isNotBlank() && confirmPassword.value.isNotBlank() && phoneNumber.value.isNotBlank()
                    ) {
                        if (password.value == confirmPassword.value) {
                            val userData = UserData(


                                firstName = firstName.value,
                                lastName = lastName.value,
                                email = email.value,
                                password = password.value,
                                phoneNumber = phoneNumber.value
                            )

                            viewModel.SignUp(
                                userData
                            )


                        } else {
                            Toast.makeText(
                                context,
                                "Password and Confirm Password do not match",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
                    }


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp,start = 60.dp, end = 60.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF010D1A))
            ) {
                Text("Signup", color = Color.White)
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 60.dp)) {
                Text("Already have an account?")
                TextButton(onClick = {
                    navController.navigate(Routes.LoginScreen)
                }) {
                    Text("Login", color = Color.Blue)
                }
            }
        }
    }
}



