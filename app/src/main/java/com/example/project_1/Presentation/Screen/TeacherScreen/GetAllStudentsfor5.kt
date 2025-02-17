package com.example.project_1.Presentation.Screen.TeacherScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import com.example.project_1.Domain.Model.Subject
import com.example.project_1.Domain.Model.SubjectDataParent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllStudentsfor5(
    viewModel: Project1ViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.getAllStudents5()
    }

    val Studentstate = viewModel.getAllStudentsState.collectAsStateWithLifecycle()
    val addmarksstate = viewModel.addMarksScreenState.collectAsStateWithLifecycle()

    var selectedStudentId by remember { mutableStateOf("") }
    var subjectName by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    if (addmarksstate.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (addmarksstate.value.error.toString().isNullOrBlank()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = addmarksstate.value.error.toString())
        }
    } else if (addmarksstate.value.addmarks != null) {
        // Navigate or perform actions when marks are added
    } else {
        Scaffold(
            topBar = {
                IconButton(
                    onClick = { /* Navigate back */ },
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
                            text = "Marks",
                            style = TextStyle(
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(start = 100.dp)
                        )
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color(0xFFF8EE95) // Custom color for the TopAppBar background
                    )
                )
            },

            content = { innerpadding ->

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                        .background(Color(0xFFF5F0C8))
                ) {
                    items(Studentstate.value.getallstudent) { student ->
                        StudentItem(
                            studentData = student,
                            onStudentSelected = {
                                selectedStudentId = student.email  // Using student email as unique ID
                                showDialog = true
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Show dialog when selectedStudentId is not empty
                if (showDialog && selectedStudentId.isNotEmpty()) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Add Marks with SubjectName") },
                        text = {
                            Column(modifier = Modifier.padding(top = 100.dp)) {
                                Text(text = "Add Marks to Student: $selectedStudentId")
                                OutlinedTextField(
                                    value = subjectName,
                                    onValueChange = { subjectName = it },
                                    label = { Text("Subject Name") }
                                )
                                OutlinedTextField(
                                    value = score.toString(),
                                    onValueChange = { score = it.toIntOrNull() ?: 0 },
                                    label = { Text("Score") },
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                                )
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    // Prepare SubjectDataParent to add marks
                                    val subjectMarks = Subject(subjectName, score)

                                    // Create SubjectDataParent with unique nodeId (student's email)
                                    val studentDataParent = SubjectDataParent(nodeId = selectedStudentId, subject = subjectMarks)

                                    // Call the viewModel to add marks to Firestore
                                    viewModel.addMarks(studentDataParent)

                                    // Reset and close the dialog
                                    subjectName = ""
                                    score = 0
                                    showDialog = false
                                }
                            ) {
                                Text("Confirm")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { showDialog = false }) {
                                Text("Cancel")
                            }
                        }
                    )
                }
            }
        )
    }
}
@Composable
fun StudentItem(studentData: StudentData, onStudentSelected: (String) -> Unit) {
    // Card for each student item
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),  // Optional elevation to give depth to the card
        shape = RoundedCornerShape(8.dp), // Rounded corners for a modern look
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onStudentSelected(studentData.email) }  // Trigger the click to open the dialog
                .fillMaxWidth()
        ) {
            // Display student's name
            Text(
                text = studentData.name,
                style = MaterialTheme.typography.bodyMedium, // Text style
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f) // Make name take up available space
            )

            // Optionally, show marks or semester info inside the card
            studentData.subjects.forEach { subject ->
                Text(
                    text = "${subject.name}: ${subject.score}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)  // Padding between subjects
                )
            }
        }
    }
}
