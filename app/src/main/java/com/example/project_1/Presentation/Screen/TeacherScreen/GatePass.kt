package com.example.project_1.Presentation.Screen.TeacherScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun gatepass() {
    val name = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val branch = remember { mutableStateOf("") }
    val time = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Gate Pass",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(start = 90.dp)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF8ED84) // Custom color for the TopAppBar background
                )

            )
        },
        content = { innerpadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
                    .background(Color(0xFFFCF7D3))
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 10.dp)
                        .background(Color(0xFF7DCAEE))
                        .clickable {  })
                Card(
                    shape = RoundedCornerShape(60.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                        .background(Color(0xFFE7C2F8)),
                    elevation = CardDefaults.cardElevation(15.dp),
                ) {

                    Image(
                        painter = painterResource(R.drawable.car),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .size(220.dp)
                    )


                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = { Text("name") },
                        placeholder = { Text("Enter your name") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle, contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 30.dp, end = 30.dp)

                    )
                    OutlinedTextField(
                        value = branch.value,
                        onValueChange = { branch.value = it },
                        label = { Text("Branch") },
                        placeholder = { Text("Enter your Branch") },
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.book),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 30.dp, end = 30.dp)

                    )

                    OutlinedTextField(
                        value = date.value,
                        onValueChange = { date.value = it },
                        label = { Text("date") },
                        placeholder = { Text("Enter your date") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange, contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 30.dp, end = 30.dp)

                    )

                    OutlinedTextField(
                        value = time.value,
                        onValueChange = { time.value = it },
                        label = { Text("time") },
                        placeholder = { Text("Enter your time") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange, contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 30.dp, end = 30.dp, bottom = 30.dp)

                    )
                    Button(
                        onClick = {},
                        modifier = Modifier.align(Alignment.End).padding(top = 10.dp,end = 40.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF020933))
                    ) {
                        Text(text = "OK")
                    }


                }
            }
        })
}