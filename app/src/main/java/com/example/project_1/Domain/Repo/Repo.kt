package com.example.project_1.Domain.Repo

import android.net.Uri
import com.example.project_1.Common.ResultState
import com.example.project_1.Data.Database.Dao
import com.example.project_1.Data.Database.PasswordManager
import com.example.project_1.Data.Network.StudentModel
import com.example.project_1.Domain.Model.Attendance
import com.example.project_1.Domain.Model.AttendanceDataParent
import com.example.project_1.Domain.Model.GatePassdata
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.StudentDataParent
import com.example.project_1.Domain.Model.Subject
import com.example.project_1.Domain.Model.SubjectDataParent
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Model.UserDataParent
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repo  {
    fun LoginWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
    fun  registerUserWithEmailAndPassword(userData: UserData):Flow<ResultState<String>>
    fun getuserById(uid:String): Flow<ResultState<UserDataParent>>
    fun userProfileImage(uri: Uri):Flow<ResultState<String>>
    fun StudentregisterUserWithEmailAndPassword(studentData: StudentData):Flow<ResultState<String>>
    fun gatepass(gatePassdata: GatePassdata):Flow<ResultState<String>>
    fun getallStudent5(): Flow<ResultState<List<StudentData>>>
    fun addMarks5(studentDataParent: SubjectDataParent):Flow<ResultState<StudentData>>
    fun addattendance(attendanceDataParent: AttendanceDataParent):Flow<ResultState<StudentData>>
    fun StudentLoginWithEmailAndPassword(studentData: StudentData): Flow<ResultState<String>>
    fun getstudentbyid(uid: String):Flow<ResultState<StudentDataParent>>
    fun getmarksbyid(uid: String):Flow<ResultState<StudentDataParent>>
    suspend fun newProvider(): Response<StudentModel>
    suspend fun upsert(passwordManager: PasswordManager)
    fun getAllPassword(): Flow<List<PasswordManager>>
    suspend fun delete(passwordManager: PasswordManager)

}