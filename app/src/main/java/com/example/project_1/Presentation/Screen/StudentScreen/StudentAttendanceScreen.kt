package com.example.project_1.Presentation.Screen.StudentScreen


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.project_1.Domain.Model.Attendance
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.json.JsonNull.content

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentAttendanceScreen(
    viewModel: Project1ViewModel = hiltViewModel(),
    firebaseAuth: FirebaseAuth,
    navController: NavController
) {
    // Fetch the logged-in user's ID
    val currentUser = firebaseAuth.currentUser
    val studentId = currentUser?.uid ?: ""

    // Fetch student data based on logged-in user's ID
    LaunchedEffect(key1 = studentId) {
        if (studentId.isNotEmpty()) {
            viewModel.getStudentmarksbyId(studentId)  // Fetch data for the logged-in student
        }
    }

    val studentDataState = viewModel.studentMarksScreenState.collectAsStateWithLifecycle()

    if (studentDataState.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (studentDataState.value.error.toString().isNullOrBlank()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = studentDataState.value.error.toString())
        }
    } else {
        Scaffold(
            topBar = {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 10.dp)
                        .background(Color(0xFF7DCAEE))
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                TopAppBar(
                    title = {
                        Text(
                            text = "Your Attendance",
                            style = TextStyle(
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(start = 5.dp)
                        )
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color(0xFFF8EE95) // Custom color for the TopAppBar background
                    )
                )
            },

            content = { innerpadding ->

                // Display the logged-in student's information
                studentDataState.value.studentmarks?.let { student ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 120.dp)
                            .background(Color(0xFFF5F0C8))
                    ) {
                        item {
                            // Display student name
                            Text(
                                text = "Student Name: ${student.studentData.name}",
                                style = MaterialTheme.typography.displayMedium,
                                modifier = Modifier.padding(top = 50.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            // Calculate and display the attendance percentage
                            val attendancePercentage =
                                calculateAttendancePercentage(student.studentData.attendance)
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(30.dp), colors = CardDefaults.cardColors(Color(
                                    0xFFEBF1A3
                                )
                                )
                            ) {
                                Text(
                                    text = "Attendance Percentage: ${
                                        "%.2f".format(
                                            attendancePercentage
                                        )
                                    }%",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(start = 40.dp)
                                )
                            }

                            // Display the subjects of the logged-in student inside Card
                            student.studentData.attendance.forEach { subject ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .background(Color(0xFFECECEC)), // Background color for the card
                                    shape = RoundedCornerShape(8.dp), // Rounded corners for the card
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Subject: ${subject.subject}",
                                            style = MaterialTheme.typography.bodyLarge,
                                            modifier = Modifier.weight(1f)
                                        )

                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

// Function to calculate attendance percentage based on list of Attendance
fun calculateAttendancePercentage(attendanceList: List<Attendance>): Float {
    val totalClasses = attendanceList.size
    val attendedClasses = attendanceList.count { it.status == "Present" } // Count "Present" status

    return if (totalClasses > 0) {
        (attendedClasses.toFloat() / totalClasses) * 100
    } else {
        0f
    }
}
