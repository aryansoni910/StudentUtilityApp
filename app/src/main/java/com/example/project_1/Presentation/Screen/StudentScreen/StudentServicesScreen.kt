package com.example.project_1.Presentation.Screen.StudentScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.R

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentServicesScreen() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Services",
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(start = 70.dp)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF8EE95)
                )
            )
        },
        content = { innerpadding ->
            // Content of the About Us page
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F0C8))
                    .padding(innerpadding)
                    .padding(top = 100.dp)


            ) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp)
                            .size(height = 150.dp, width = 160.dp)
                            .clickable {
                                openWebPage(
                                    context,
                                    "https://gyangangamoodle.in/login/index.php?testsession=15948"
                                )
                            },
                        colors = CardDefaults.cardColors(
                            Color(0xFFF8ED89)
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.education),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 20.dp)
                        )
                        Text(
                            "Moddle",
                            modifier = Modifier.padding(top = 20.dp, start = 30.dp),
                            style = TextStyle(fontSize = 30.sp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp)
                            .size(height = 150.dp, width = 160.dp)
                            .clickable {
                                openWebPage(
                                    context,
                                    "https://www.tribal.mp.gov.in/mptaas/Login/Login"
                                )
                            },
                        colors = CardDefaults.cardColors(
                            Color(0xFFF8ED89)
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.scholarship),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 20.dp)
                        )
                        Text(
                            "Scholarship",
                            modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                            style = TextStyle(fontSize = 25.sp)
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .padding(top = 70.dp, start = 20.dp)
                            .size(height = 150.dp, width = 160.dp)
                            .clickable {
                                openWebPage(
                                    context,
                                    "https://www.rgpv.ac.in/Login/StudentLogin.aspx"
                                )
                            },
                        colors = CardDefaults.cardColors(
                            Color(0xFFF8ED89)
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.university),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 20.dp)
                        )
                        Text(
                            "Rgpv",
                            modifier = Modifier.padding(top = 20.dp, start = 45.dp),
                            style = TextStyle(fontSize = 30.sp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .padding(top = 70.dp, start = 20.dp)
                            .size(height = 150.dp, width = 160.dp)
                            .clickable {
                                openWebPage(
                                    context,
                                    "https://gyangangagroupfees.in/index_student.php#!/login"
                                )
                            },
                        colors = CardDefaults.cardColors(
                            Color(0xFFF8ED89)
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.receipt),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 20.dp)
                        )
                        Text(
                            "Fees",
                            modifier = Modifier.padding(top = 20.dp, start = 45.dp),
                            style = TextStyle(fontSize = 30.sp)
                        )
                    }
                }

            }


        })
}

// Function to open the URL in a web browser
fun openWebPage(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}


