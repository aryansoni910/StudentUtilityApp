package com.example.project_1.Domain.Repo

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.UserData
import kotlinx.coroutines.flow.Flow

interface Repo  {
    fun LoginWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
    fun  registerUserWithEmailAndPassword(userData: UserData):Flow<ResultState<String>>
}