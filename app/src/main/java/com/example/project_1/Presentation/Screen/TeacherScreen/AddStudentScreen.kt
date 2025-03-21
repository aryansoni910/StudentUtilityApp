package com.example.project_1.Presentation.Screen.TeacherScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import com.example.project_1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(navController: NavController, viewModel: Project1ViewModel = hiltViewModel()) {
    val context = LocalContext.current


    val state = viewModel.addStudentState.collectAsStateWithLifecycle()
    if (state.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.value.error.toString().isNullOrBlank()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.value.error.toString())
        }
    } else if (state.value.studentdata != null) {
        navController.navigate(Routes.AddStudent)

    } else {


        val name = remember { mutableStateOf("") }
        val enrollentno = remember { mutableStateOf("") }
        val branch = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val sem = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }


        Scaffold(
            topBar = {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 10.dp)
                        .background(Color(0xFF7DCAEE))
                        .clickable  { navController.navigate(Routes.TeacherHomeScreen) {
                        popUpTo(Routes.TeacherHomeScreen) { inclusive = true }
                    }})
                TopAppBar(
                    title = {
                        Text(
                            text = "Add Student",
                            style = TextStyle(
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic
                            ), modifier = Modifier.padding(5.dp)
                        )
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color(0xFFE3B1FD) // Custom color for the TopAppBar background
                    )


                )

            },
            content = { innerpadding ->
              Box(Modifier.fillMaxSize()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerpadding)
                        .background(Color(0xFFC0E7F8))
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 10.dp)
                            .background(Color(0xFF8899F5))
                            .clickable { navController.navigate(Routes.TeacherHomeScreen) })

                    Image(
                        painter = painterResource(R.drawable.graduated),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(20.dp)
                            .size(80.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = { Text("Name", color = Color.Black) },
                        placeholder = { Text("Enter your Name", color = Color.Black) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person, contentDescription = null, tint = Color.Black
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)

                    )

                    OutlinedTextField(
                        value = enrollentno.value,
                        onValueChange = { enrollentno.value = it },
                        label = { Text("Enrollment number", color = Color.Black) },
                        placeholder = { Text("Enter your Enrollment number", color = Color.Black) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.AccountBox, contentDescription = null, tint = Color.Black
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)

                    )


                    OutlinedTextField(
                        value = branch.value,
                        onValueChange = { branch.value = it },
                        label = { Text("Branch", color = Color.Black) },
                        placeholder = { Text("Enter your Branch", color = Color.Black) },
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.book),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)

                    )


                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Password", color = Color.Black) },
                        placeholder = { Text("Enter your Password", color = Color.Black) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange, contentDescription = null, tint = Color.Black
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)

                    )


                    OutlinedTextField(
                        value = sem.value,
                        onValueChange = { sem.value = it },
                        label = { Text("Semester", color = Color.Black) },
                        placeholder = { Text("Enter your Semester", color = Color.Black) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.presentation),
                                contentDescription = null, tint = Color.Black,
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)

                    )

                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text("Email", color = Color.Black) },
                        placeholder = { Text("Enter your Email", color = Color.Black) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email, contentDescription = null, tint = Color.Black
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 30.dp)

                    )


                    Button(
                        onClick = {

                            if (
                                name.value.isNotBlank() && enrollentno.value.isNotBlank() && password.value.isNotBlank()
                                && sem.value.isNotBlank() && email.value.isNotBlank() && branch.value.isNotBlank()
                            ) {
                                val addStudentData = StudentData(


                                    name = name.value,
                                    enrollmentNumber = enrollentno.value,
                                    email = email.value,
                                    password = password.value,
                                    branch = branch.value,
                                    sem = sem.value
                                )

                                viewModel.AddStudent(
                                    addStudentData
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please Fill All Fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 60.dp, end = 60.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF010D1A))
                    ) {

                        Text(text = "Submit")
                    }

                }
                }
            })
    }
}