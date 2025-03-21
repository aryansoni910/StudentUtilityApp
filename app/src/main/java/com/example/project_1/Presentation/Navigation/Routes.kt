package com.example.project_1.Presentation.Navigation

import kotlinx.serialization.Serializable



  sealed class Routes {
        @Serializable
        object LoginScreen

        @Serializable
        object SingUpScreen

        @Serializable
        object TeacherHomeScreen

        @Serializable
        object getstarted

        @Serializable
        object choice

        @Serializable
        object YourProfile

        @Serializable
        object AddStudent

        @Serializable
        object GatePass

        @Serializable
        object Help

        @Serializable
        object Sem

        @Serializable
        object Marks5

        @Serializable
        object LoadingScreen

        @Serializable
        object AttendanceSem

        @Serializable
        object AttendanceScreen

        @Serializable
        object StudentLogin

        @Serializable
        object StudentHomeScreen

        @Serializable
        object StudentProfileScreen

        @Serializable
        object StudentmarksScreen

        @Serializable
        object Studentattendancescreen

        @Serializable
        object StudentNewsScreen

        @Serializable
        object StudentServicesScreen

        @Serializable
        object CollegeMap

        @Serializable
        object PasswordManagerScreen

        @Serializable
        object AddPasswordManagerScreen
   }