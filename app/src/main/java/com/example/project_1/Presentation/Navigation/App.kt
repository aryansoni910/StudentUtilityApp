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
import com.example.project_1.Presentation.Screen.TeacherScreen.Login
import com.example.project_1.Presentation.Screen.TeacherScreen.ProfileScreen
import com.example.project_1.Presentation.Screen.TeacherScreen.SingUpScreenUi
import com.example.project_1.Presentation.Screen.TeacherScreen.TeacherHomeScreen
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
        composable<Routes.TeacherHomeScreen> { TeacherHomeScreen(navController) }
        composable<Routes.SingUpScreen> {
            SingUpScreenUi(
                viewModel = hiltViewModel(), navController = navController)
        }
        composable<Routes.YourProfile> {
            ProfileScreen(firebaseAuth = FirebaseAuth.getInstance(), navController = navController)
        }
    }


}