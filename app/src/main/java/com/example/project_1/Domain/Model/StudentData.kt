package com.example.project_1.Domain.Model

import javax.security.auth.Subject

data class StudentData(
    val name : String = "",
    val enrollmentNumber :String = "",
    val branch : String = "",
    val password : String = "",
    val sem : String = "",
    val email : String = "",
    val StudentImage : String = "",
    val subjects : List<com.example.project_1.Domain.Model.Subject> = listOf()
)

data class Subject(
    val name: String = "",
    val score :Int = 0
)

data class SubjectDataParent(
    val nodeId : String = "",
    val subject: com.example.project_1.Domain.Model.Subject
)