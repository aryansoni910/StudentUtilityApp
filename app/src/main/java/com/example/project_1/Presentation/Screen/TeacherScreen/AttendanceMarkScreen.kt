package com.example.project_1.Presentation.Screen.TeacherScreen

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.project_1.Domain.Model.Attendance
import com.example.project_1.Domain.Model.AttendanceDataParent
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceMarkScreen(
    viewModel: Project1ViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        viewModel.getAllStudents5()
    }
    val context = LocalContext.current

    val Studentstate = viewModel.getAllStudentsState.collectAsState()
    val Attendancedata = viewModel.addAttendanceScreenState.collectAsState()

    var selectedSubject by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance().time) }

    // Manage attendance status for each student
    val studentStatusMap = remember { mutableStateOf<Map<String, String>>(emptyMap()) }

    // Set default status for each student to "Absent"
    if (Studentstate.value.getallstudent.isNotEmpty()) {
        studentStatusMap.value = Studentstate.value.getallstudent.associate {
            it.email to "Absent"
        }
    }

    // Update status of a specific student
    fun updateStudentStatus(studentEmail: String, status: String) {
        studentStatusMap.value = studentStatusMap.value.toMutableMap().apply {
            put(studentEmail, status)
        }
    }

    if (Studentstate.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (Studentstate.value.error.toString().isNullOrBlank()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = Studentstate.value.error.toString())
        }
    } else {
        if (Attendancedata.value.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (Attendancedata.value.error.toString().isNullOrBlank()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = Attendancedata.value.error.toString())
            }
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Attendance",
                                style = TextStyle(
                                    fontSize = 50.sp,
                                    fontWeight = FontWeight.Bold
                                ), modifier = Modifier.padding(start = 60.dp)
                            )
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color(0xFFF8EE95)
                        )
                    )
                },
                content = { innerpadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F0C8))
                            .padding(top = 100.dp)
                    ) {
                        // Subject Input
                        OutlinedTextField(
                            value = selectedSubject,
                            onValueChange = { selectedSubject = it },
                            label = { Text("Subject") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )

                     Row {
                         // Date Picker Button
                         Button(onClick = {
                             val calendar = Calendar.getInstance()
                             val datePickerDialog = DatePickerDialog(
                                 context,
                                 { _, year, month, dayOfMonth ->
                                     calendar.set(year, month, dayOfMonth)
                                     selectedDate = calendar.time
                                 },
                                 calendar.get(Calendar.YEAR),
                                 calendar.get(Calendar.MONTH),
                                 calendar.get(Calendar.DAY_OF_MONTH)
                             )
                             datePickerDialog.show()
                         }, modifier = Modifier.padding(16.dp)) {
                             Text("Select Date: ${SimpleDateFormat("yyyy-MM-dd").format(selectedDate)}")
                         }
                         Button(
                             onClick = {
                                 Studentstate.value.getallstudent.forEach { student ->
                                     val attendance = Attendance(
                                         studentName = student.name,
                                         subject = selectedSubject,
                                         date = selectedDate,
                                         status = studentStatusMap.value[student.email] ?: "Absent"
                                     )
                                     val attendancedataParent = AttendanceDataParent(
                                         nodeId = student.email,
                                         attendance = attendance,
                                     )
                                     viewModel.addAttendance(attendancedataParent)
                                     navController.popBackStack()
                                     navController.navigate(Routes.AttendanceSem)
                                     {
                                         popUpTo<Routes.AttendanceSem>{inclusive = true}
                                     }
                                 }
                             },
                             modifier = Modifier
                                 .padding(16.dp)
                         ) {
                             Text("Submit All Attendance")
                         }
                     }
                        Spacer(modifier = Modifier.height(16.dp))


                        // LazyColumn for Students
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(Studentstate.value.getallstudent) { student ->
                                StudentItems(
                                    studentData = student,
                                    selectedDate = selectedDate,
                                    selectedSubject = selectedSubject,
                                    selectedStatus = studentStatusMap.value[student.email]
                                        ?: "Absent",
                                    onStatusChange = { status ->
                                        updateStudentStatus(
                                            student.email,
                                            status
                                        )
                                    }
                                )
                            }
                        }

                        // Submit Button to save attendance for all students

                    }
                }
            )
        }
    }
}

@Composable
fun StudentItems(
    studentData: StudentData,
    selectedDate: Date,
    selectedSubject: String,
    selectedStatus: String,
    onStatusChange: (String) -> Unit

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .size(height = 70.dp, width = 400.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = studentData.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )



    // Attendance controls for each student


            RadioButton(
                selected = selectedStatus == "Present",
                onClick = { onStatusChange("Present") }
            )
            Text("Present")
            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = selectedStatus == "Absent",
                onClick = { onStatusChange("Absent") }
            )
            Text("Absent")
        }

        Spacer(modifier = Modifier.height(16.dp))

//         Submit Button to save attendance

    }
}




