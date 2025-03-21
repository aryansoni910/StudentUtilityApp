package com.example.project_1.Presentation.Screen.TeacherScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_1.Presentation.Navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttdenceSemScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Semesters", color = Color.Black,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        ), modifier = Modifier.padding(10.dp)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFE3B1FD) // Custom color for the TopAppBar background
                )

            )
        },
        content = { innerpadding ->
            Box(modifier = Modifier.fillMaxSize()){

            Column(modifier = Modifier.fillMaxSize()
                .padding(innerpadding)
                .background(Color(0xFFFCF9E7))) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.LoadingScreen) },
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "1th ", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.LoadingScreen)},
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "2th", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.LoadingScreen)},
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "3th ", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.LoadingScreen)},
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "4th", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.AttendanceScreen) },
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "5th ", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.LoadingScreen)},
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "6th", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.LoadingScreen)},
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "7th ", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(height = 130.dp, width = 160.dp)
                            .clickable { navController.navigate(Routes.LoadingScreen)},
                        colors = CardDefaults.cardColors(
                            Color(0xFFB9F8F1)
                        )
                    ) {
                        Text(
                            "8th", style = TextStyle(
                                fontSize = 60.sp, fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 30.dp, start = 40.dp)
                        )
                    }
                }
            }
        }}
    )

}
