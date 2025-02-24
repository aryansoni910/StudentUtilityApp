package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.StudentDataParent
import com.example.project_1.Domain.Model.SubjectDataParent
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentMarksUseCase @Inject constructor(val repo: Repo) {

    fun StudentMarks(uid:String) : Flow<ResultState<StudentDataParent>> {
        return repo.getmarksbyid(uid)
    }
}