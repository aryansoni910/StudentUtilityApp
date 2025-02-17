package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.UserDataParent
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllStudents5UseCase @Inject constructor(val repo: Repo) {

    fun getAllStudents5() : Flow<ResultState<List<StudentData>>> {
        return repo.getallStudent5()
    }
}