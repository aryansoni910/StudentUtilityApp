package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class  StudentLoginUsecase  @Inject constructor(private val repo: Repo) {

    fun studentloginUser(studentData: StudentData) : Flow<ResultState<String>> {
        return repo.StudentLoginWithEmailAndPassword(studentData)
    }
}