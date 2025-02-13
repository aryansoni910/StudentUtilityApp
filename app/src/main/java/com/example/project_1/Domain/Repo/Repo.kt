package com.example.project_1.Domain.Repo

import android.net.Uri
import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Model.UserDataParent
import kotlinx.coroutines.flow.Flow

interface Repo  {
    fun LoginWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
    fun  registerUserWithEmailAndPassword(userData: UserData):Flow<ResultState<String>>
    fun getuserById(uid:String): Flow<ResultState<UserDataParent>>
    fun userProfileImage(uri: Uri):Flow<ResultState<String>>
}