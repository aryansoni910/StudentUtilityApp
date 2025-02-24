package com.example.project_1.Presentation


import Choice
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.project_1.Presentation.Navigation.App
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.Screen.GetStarted
import com.example.project_1.Presentation.Screen.StudentScreen.StudentHomeScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.AddStudentScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.AttendanceMarkScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.ChatBox
import com.example.project_1.Presentation.Screen.TeacherScreen.GetAllStudentsfor5
import com.example.project_1.Presentation.Screen.TeacherScreen.Login
import com.example.project_1.Presentation.Screen.TeacherScreen.SingUpScreenUi
import com.example.project_1.Presentation.Screen.TeacherScreen.TeacherHomeScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.markssem
import com.example.project_1.ui.theme.Project_1Theme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App()
                }
            }
        }
    }
}
