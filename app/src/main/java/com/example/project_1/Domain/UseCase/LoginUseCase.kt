package com.example.project_1.Domain.UseCase


import android.util.Log
import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase  @Inject constructor(private val repo: Repo) {

    fun loginUser(userData: UserData) : Flow<ResultState<String>> {
        return repo.LoginWithEmailAndPassword(userData)
    }
}