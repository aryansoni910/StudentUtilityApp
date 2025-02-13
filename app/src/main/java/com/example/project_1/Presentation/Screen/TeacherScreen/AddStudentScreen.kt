package com.example.project_1.Presentation.Screen.TeacherScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_1.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddStudentScreen(modifier: Modifier = Modifier) {


    val name = remember { mutableStateOf("") }
    val enrollentno = remember { mutableStateOf("") }
    val branch = remember { mutableStateOf("") }
    val year = remember { mutableStateOf("") }
    val sem = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Student",
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(start = 40.dp)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFFAF2AA) // Custom color for the TopAppBar background
                )

            )
        },
        content = { innerpadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
                    .background(Color(0xFFFCF7D3))
                    .padding(top = 20.dp)
            ) {


                Image(
                    painter = painterResource(R.drawable.graduated),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .size(140.dp)
                        .align(Alignment.CenterHorizontally)
                )
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Name") },
                    placeholder = { Text("Enter your Name") },
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
                    value = enrollentno.value,
                    onValueChange = { enrollentno.value = it },
                    label = { Text("Enrollment number") },
                    placeholder = { Text("Enter your Enrollment number") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountBox, contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)

                )


                OutlinedTextField(
                    value = branch.value,
                    onValueChange = { branch.value = it },
                    label = { Text("Branch") },
                    placeholder = { Text("Enter your Branch") },
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
                    value = year.value,
                    onValueChange = { year.value = it },
                    label = { Text("Year") },
                    placeholder = { Text("Enter your Year") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange, contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)

                )


                OutlinedTextField(
                    value = sem.value,
                    onValueChange = { sem.value = it },
                    label = { Text("Semester") },
                    placeholder = { Text("Enter your Semester") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.presentation),
                            contentDescription = null,
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
                    label = { Text("Email") },
                    placeholder = { Text("Enter your Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email, contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)

                )


                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 60.dp, end = 60.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF010D1A))
                ) {

                    Text(text = "Submit")
                }


            }
        })
}