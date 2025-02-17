package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.Subject
import com.example.project_1.Domain.Model.SubjectDataParent
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddMarks5UseCase @Inject constructor(private val repo: Repo) {

    fun addMarks(studentDataParent: SubjectDataParent) : Flow<ResultState<StudentData>> {
        return repo.addMarks5(studentDataParent)
    }
}