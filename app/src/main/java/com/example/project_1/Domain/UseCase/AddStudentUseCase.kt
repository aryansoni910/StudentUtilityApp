package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddStudentUseCase @Inject constructor(private val repo: Repo) {

    fun addStudentdata(studentData: StudentData) : Flow<ResultState<String>> {
        return repo.StudentregisterUserWithEmailAndPassword(studentData)
    }
}