package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.AttendanceDataParent
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.SubjectDataParent
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddAtendanceUseCase @Inject constructor(private val repo: Repo) {

    fun addAttendance(attendanceDataParent: AttendanceDataParent) : Flow<ResultState<StudentData>> {
        return repo.addattendance(attendanceDataParent)
    }
}