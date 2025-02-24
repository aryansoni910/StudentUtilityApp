package com.example.project_1.Domain.Model

import java.util.Date

data class StudentData(
    val name : String = "",
    val enrollmentNumber :String = "",
    val branch : String = "",
    val password : String = "",
    val sem : String = "",
    val email : String = "",
    val StudentImage : String = "",
    val subjects : List<com.example.project_1.Domain.Model.Subject> = listOf(),
    val attendance : List<Attendance> = listOf()
)

data class Attendance(
    val studentName: String = "",
    val subject: String = "",
    val date: Date = Date(),
    val status: String = ""
)


data class Subject(
    val name: String = "",
    val score :Int = 0
)

data class AttendanceDataParent(
    val nodeId: String = "",
    val attendance: Attendance
)

data class SubjectDataParent(
    val nodeId: String = "",
    val subject: Subject
)

data class StudentDataParent(
    val nodeId: String,
    val studentData: StudentData
)