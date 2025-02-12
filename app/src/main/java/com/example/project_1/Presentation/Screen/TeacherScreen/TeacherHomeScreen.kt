package com.example.project_1.Presentation.Screen.TeacherScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import com.example.project_1.R
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Teacher Dashboard",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(start = 40.dp)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFFAF2AA) // Custom color for the TopAppBar background
                )

            )
        },
        content = { innerpadding-> // Add paddingValues here
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(innerpadding)
                    .background(color = Color.LightGray)
                    .background(
                        Color(0xFFFCF7D3)
                    )
                    .padding(top= 60.dp)
            ) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .size(height = 180.dp, width = 180.dp)
                            .padding(start = 15.dp).clickable {  }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.attendance),
                            contentDescription = null,
                            modifier = Modifier
                                .size(height = 120.dp, width = 120.dp)
                                .align(Alignment.Start)
                                .padding(top = 20.dp)
                        )
                        Text(
                            text = "Attendance", style = TextStyle(
                                fontSize = 30.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 20.dp, start = 5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(
                        modifier = Modifier
                            .size(height = 180.dp, width = 180.dp)
                            .padding(start = 10.dp).clickable {  }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.marks),
                            contentDescription = null,
                            modifier = Modifier
                                .size(height = 120.dp, width = 120.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp)
                        )
                        Text(
                            text = "Marks", style = TextStyle(
                                fontSize = 30.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 20.dp, start = 35.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 40.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .size(height = 180.dp, width = 180.dp)
                            .padding(start = 15.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gatepass),
                            contentDescription = null,
                            modifier = Modifier
                                .size(height = 120.dp, width = 120.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp)
                        )
                        Text(
                            text = "GatePass", style = TextStyle(
                                fontSize = 30.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 20.dp, start = 15.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(
                        modifier = Modifier
                            .size(height = 180.dp, width = 180.dp)
                            .padding(start = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.friend),
                            contentDescription = null,
                            modifier = Modifier
                                .size(height = 120.dp, width = 120.dp)
                                .align(Alignment.CenterHorizontally).padding(top = 10.dp)
                        )
                        Text(
                            text = "AddStudent", style = TextStyle(
                                fontSize = 30.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 20.dp, start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 40.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .size(height = 180.dp, width = 180.dp)
                            .padding(start = 15.dp).clickable {navController.navigate(Routes.YourProfile)
                            }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.student),
                            contentDescription = null,
                            modifier = Modifier
                                .size(height = 120.dp, width = 120.dp)
                                .align(Alignment.CenterHorizontally).padding(top = 10.dp)
                        )
                        Text(
                            text = "Your Profile", style = TextStyle(
                                fontSize = 30.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 20.dp, start = 5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(
                        modifier = Modifier
                            .size(height = 180.dp, width = 180.dp)
                            .padding(start = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.report),
                            contentDescription = null,
                            modifier = Modifier
                                .size(height = 120.dp, width = 120.dp)
                                .align(Alignment.CenterHorizontally).padding(top = 20.dp)
                        )
                        Text(
                            text = "Report", style = TextStyle(
                                fontSize = 30.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 20.dp, start = 40.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 30.dp))
            }
        }
    )
}
