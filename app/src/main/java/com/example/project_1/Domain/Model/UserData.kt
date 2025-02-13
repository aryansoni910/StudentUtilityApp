package com.example.project_1.Domain.Model

data class UserData(
    val email: String = "",
    val password: String = "",
    val lastName: String = "",
    val firstName: String = "",
    val phoneNumber: String = "",
    val profileImage: String =""
)

data class UserDataParent(val nodeId: String = "", val userData: UserData = UserData())