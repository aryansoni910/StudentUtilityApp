package com.example.project_1.Presentation.Navigation

import Choice
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project_1.Presentation.Screen.GetStarted
import com.example.project_1.Presentation.Screen.StudentScreen.StudentAttendanceScreen
import com.example.project_1.Presentation.Screen.StudentScreen.StudentHomeScreen
import com.example.project_1.Presentation.Screen.StudentScreen.StudentLoginScreen
import com.example.project_1.Presentation.Screen.StudentScreen.StudentMarksScreen
import com.example.project_1.Presentation.Screen.StudentScreen.StudentNewsScreen
import com.example.project_1.Presentation.Screen.StudentScreen.StudentProfileScreen
import com.example.project_1.Presentation.Screen.StudentScreen.StudentServicesScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.AddStudentScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.AttdenceSemScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.AttendanceMarkScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.ChatBox
import com.example.project_1.Presentation.Screen.TeacherScreen.GatePassScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.GetAllStudentsfor5
import com.example.project_1.Presentation.Screen.TeacherScreen.LoadingScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.Login
import com.example.project_1.Presentation.Screen.TeacherScreen.ProfileScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.SingUpScreenUi
import com.example.project_1.Presentation.Screen.TeacherScreen.TeacherHomeScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.markssem
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import com.google.firebase.auth.FirebaseAuth


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.getstarted) {
        composable<Routes.choice> {
            Choice(
                navController = navController
            )
        }
        composable<Routes.getstarted> {
            GetStarted(
                navController = navController
            )
        }
        composable<Routes.LoginScreen> {
            Login(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
        composable<Routes.TeacherHomeScreen> {
            TeacherHomeScreen(navController)
        }
        composable<Routes.SingUpScreen> {
            SingUpScreenUi(
                viewModel = hiltViewModel(), navController = navController
            )
        }
        composable<Routes.YourProfile> {
            ProfileScreen(firebaseAuth = FirebaseAuth.getInstance(), navController = navController)
        }
        composable<Routes.AddStudent> {
            AddStudentScreen(navController)
        }
        composable<Routes.GatePass> {
            GatePassScreen(navController)
        }
        composable<Routes.Help> {
            ChatBox()
        }
        composable<Routes.Sem> {
            markssem(navController)
        }
        composable<Routes.Marks5> {
            GetAllStudentsfor5(navController = navController)
        }
        composable<Routes.LoadingScreen> {
            LoadingScreen()
        }

        composable<Routes.AttendanceSem> {
            AttdenceSemScreen(navController)
        }

        composable<Routes.AttendanceScreen> {
            AttendanceMarkScreen(
                navController = navController
            )
        }

        composable<Routes.StudentLogin> {
            StudentLoginScreen(navController = navController)
        }

        composable<Routes.StudentHomeScreen> {
            StudentHomeScreen(navController)
        }

        composable<Routes.StudentProfileScreen> {
            StudentProfileScreen(firebaseAuth = FirebaseAuth.getInstance(), navController = navController)
        }

        composable<Routes.StudentmarksScreen> {
           StudentMarksScreen (firebaseAuth = FirebaseAuth.getInstance(), navController = navController)
        }

        composable<Routes.Studentattendancescreen> {
            StudentAttendanceScreen(firebaseAuth = FirebaseAuth.getInstance(), navController = navController)
        }

        composable<Routes.StudentNewsScreen> {
            StudentNewsScreen()
        }
        composable<Routes.StudentServicesScreen> {
            StudentServicesScreen()
        }
    }


}