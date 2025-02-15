package com.example.project_1.Presentation.Screen.TeacherScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun markssem() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Marks",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(start = 120.dp)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFFAEB69) // Custom color for the TopAppBar background
                )

            )
        },
        content = { innerpadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
                    .background(Color(0xFFFCF3A8))
            ) {
                Card(
                    modifier = Modifier
                        .padding(top = 100.dp, start = 20.dp)
                        .size(height = 150.dp, width = 160.dp)
                        .clickable { },
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
                        .padding(top = 100.dp, start = 20.dp, end = 20.dp)
                        .size(height = 150.dp, width = 160.dp)
                        .clickable { },
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
        }
    )

}