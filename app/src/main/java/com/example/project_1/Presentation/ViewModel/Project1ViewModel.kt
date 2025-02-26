package com.example.project_1.Presentation.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.health.connect.datatypes.ExerciseRoute
import android.location.Location
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.Common.ApiKey
import com.example.project_1.Common.ResultState
import com.example.project_1.Data.Network.StudentModel
import com.example.project_1.Data.Repo.RepoImpl
import com.example.project_1.Domain.Model.AttendanceDataParent
import com.example.project_1.Domain.Model.ChatBotEnum
import com.example.project_1.Domain.Model.GatePassdata
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.StudentDataParent
import com.example.project_1.Domain.Model.SubjectDataParent
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Model.UserDataParent
import com.example.project_1.Domain.Model.chatbotData
import com.example.project_1.Domain.Repo.Repo
import com.example.project_1.Domain.UseCase.AddAtendanceUseCase
import com.example.project_1.Domain.UseCase.AddMarks5UseCase
import com.example.project_1.Domain.UseCase.AddStudentUseCase
import com.example.project_1.Domain.UseCase.GatePassUseCase
import com.example.project_1.Domain.UseCase.GetAllStudents5UseCase
import com.example.project_1.Domain.UseCase.LoginUserUseCase
import com.example.project_1.Domain.UseCase.ProfileScreenUsecase
import com.example.project_1.Domain.UseCase.SignUPUseCase
import com.example.project_1.Domain.UseCase.StudentLoginUsecase
import com.example.project_1.Domain.UseCase.StudentMarksUseCase
import com.example.project_1.Domain.UseCase.StudentProfileScreenUseCase
import com.example.project_1.Domain.UseCase.UserProfileImageUseCase
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MarkerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Project1ViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val signUPUseCase: SignUPUseCase,
    private val profileScreenUsecase: ProfileScreenUsecase,
    private val userProfileImageUseCase: UserProfileImageUseCase,
    private val addStudentUseCase: AddStudentUseCase,
    private val gatePassUseCase: GatePassUseCase,
    private val getAllStudents5UseCase: GetAllStudents5UseCase,
    private val addMarks5UseCase: AddMarks5UseCase,
    private val addAtendanceUseCase: AddAtendanceUseCase,
    private val studentLoginUsecase: StudentLoginUsecase,
    private val studentMarksUseCase: StudentMarksUseCase,
    private val studentProfileScreenUseCase: StudentProfileScreenUseCase,
    private val repo: Repo,
    private val application: Application
) : ViewModel() {
    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _SignUpState = MutableStateFlow(SignUpScrenState())
    val SignUpState = _SignUpState.asStateFlow()

    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileStateScreen = _profileScreenState.asStateFlow()

    private val _userProfileImageState = MutableStateFlow((UploadUserProfileImageState()))
    val userProfileImageState = _userProfileImageState.asStateFlow()

    private val _addStudentState = MutableStateFlow(AddStudentScreenState())
    val addStudentState = _addStudentState.asStateFlow()

    private val _gatePassState = MutableStateFlow(GatePassScreenState())
    val gatePassState = _gatePassState.asStateFlow()

    private val _getAllStudentsState = MutableStateFlow(GetAllStudentsState())
    val getAllStudentsState = _getAllStudentsState.asStateFlow()

    private val _addMarksScreenState = MutableStateFlow(AddMarksScreenState())
    val addMarksScreenState = _addMarksScreenState.asStateFlow()

    private val _addAttendanceScreenState = MutableStateFlow(AddAttenedanceScreenState())
    val addAttendanceScreenState = _addAttendanceScreenState.asStateFlow()

    private val _studentLoginStateScreen = MutableStateFlow(StudentLoginScreenState())
    val studentStateScreen = _studentLoginStateScreen.asStateFlow()


    private val _studentProfileScreenState = MutableStateFlow(StudentProfileScreenState())
    val studentProfileScreenState = _studentProfileScreenState.asStateFlow()

    private val _studentMarksScreenState = MutableStateFlow((StudentMarksScreenState()))
    val studentMarksScreenState = _studentMarksScreenState.asStateFlow()

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)



    val res = mutableStateOf<StudentModel?>(null)


    init {
        viewModelScope.launch {
            res.value = getNews(
                repo
            )
        }
    }

    private val genAI by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = ApiKey
        )
    }
    val list by lazy {
        mutableStateListOf<chatbotData>()
    }




    suspend fun getNews(repo: Repo): StudentModel?{
        return repo.newProvider().body()
    }





    fun sendMessage(message: String) = viewModelScope.launch {
        val chat = genAI.startChat()

        list.add(chatbotData(message, ChatBotEnum.User.role))

        chat.sendMessage(
            content(ChatBotEnum.User.role) { text(message) }
        ).text?.let {
            list.add(chatbotData(it, ChatBotEnum.Model.role))
        }
    }



    fun Studentlogin(studentData: StudentData) {
        viewModelScope.launch {
            studentLoginUsecase.studentloginUser(studentData).collect {
                when (it) {
                    is ResultState.Error -> _studentLoginStateScreen.value =
                        StudentLoginScreenState(error = it.message)

                    ResultState.Loading -> _studentLoginStateScreen.value =
                        StudentLoginScreenState(isLoading = true)

                    is ResultState.Success -> _studentLoginStateScreen.value =
                        StudentLoginScreenState(studentData = it.data)

                }
            }
        }
    }


    fun addMarks(studentDataParent: SubjectDataParent) {
        viewModelScope.launch {
            addMarks5UseCase.addMarks(studentDataParent).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addMarksScreenState.value =
                            AddMarksScreenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _addMarksScreenState.value =
                            AddMarksScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addMarksScreenState.value =
                            AddMarksScreenState(addmarks = it.data.toString())
                    }
                }
            }
        }
    }

    fun addAttendance(attendanceDataParent: AttendanceDataParent) {
        viewModelScope.launch {
            addAtendanceUseCase.addAttendance(attendanceDataParent).collect {
                when (it) {
                    is ResultState.Error -> _addAttendanceScreenState.value =
                        AddAttenedanceScreenState(error = it.message)

                    ResultState.Loading -> _addAttendanceScreenState.value =
                        AddAttenedanceScreenState(isLoading = true)

                    is ResultState.Success -> _addAttendanceScreenState.value =
                        AddAttenedanceScreenState(addattendance = it.data.toString())
                }
            }
        }
    }

    fun login(userData: UserData) {
        viewModelScope.launch {
            loginUserUseCase.loginUser(userData).collect {
                when (it) {
                    is ResultState.Error -> _loginScreenState.value =
                        LoginScreenState(error = it.message)

                    ResultState.Loading -> _loginScreenState.value =
                        LoginScreenState(isLoading = true)

                    is ResultState.Success -> _loginScreenState.value =
                        LoginScreenState(userData = it.data)

                }
            }
        }
    }

    fun getAllStudents5() {
        viewModelScope.launch {
            getAllStudents5UseCase.getAllStudents5().collect {
                when (it) {
                    is ResultState.Error -> {
                        // Update only the error message
                        _getAllStudentsState.value =
                            GetAllStudentsState(error = it.message ?: "Unknown error")
                    }

                    ResultState.Loading -> {
                        // Set loading state
                        _getAllStudentsState.value = GetAllStudentsState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        // Set the list of students (it.data should be a List<StudentData>)
                        _getAllStudentsState.value = GetAllStudentsState(getallstudent = it.data)
                    }
                }
            }
        }
    }


    fun gatepass(gatePassdata: GatePassdata) {
        viewModelScope.launch {
            gatePassUseCase.gatepass(gatePassdata).collect {
                when (it) {
                    is ResultState.Error -> _gatePassState.value =
                        GatePassScreenState(error = it.message)

                    ResultState.Loading -> _gatePassState.value =
                        GatePassScreenState(isLoading = true)

                    is ResultState.Success -> _gatePassState.value =
                        GatePassScreenState(gatepassdata = it.data)
                }
            }
        }
    }

    fun getuserById(uid: String) {
        viewModelScope.launch {
            profileScreenUsecase.getuserById(uid).collect {
                when (it) {
                    is ResultState.Error -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            error = it.message
                        )

                    }

                    ResultState.Loading -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }
        }
    }

    fun getStudentbyId(uid: String) {
        viewModelScope.launch {
            studentProfileScreenUseCase.getStudentById(uid).collect {
                when (it) {
                    is ResultState.Error -> {
                        _studentProfileScreenState.value = _studentProfileScreenState.value.copy(
                            isLoading = false,
                            error = it.message
                        )

                    }

                    ResultState.Loading -> {
                        _studentProfileScreenState.value = _studentProfileScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _studentProfileScreenState.value = _studentProfileScreenState.value.copy(
                            isLoading = false,
                            studentdata = it.data
                        )
                    }
                }

            }
        }
    }


    fun getStudentmarksbyId(uid: String) {
        viewModelScope.launch {
            studentMarksUseCase.StudentMarks(uid).collect {
                when (it) {
                    is ResultState.Error -> {
                        _studentMarksScreenState.value = _studentMarksScreenState.value.copy(
                            isLoading = false,
                            error = it.message
                        )

                    }

                    ResultState.Loading -> {
                        _studentMarksScreenState.value = _studentMarksScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _studentMarksScreenState.value = _studentMarksScreenState.value.copy(
                            isLoading = false,
                            studentmarks = it.data
                        )
                    }
                }

            }
        }
    }

    fun upLoadUserProfileImage(uri: Uri) {
        viewModelScope.launch {
            userProfileImageUseCase.userProfileImage(uri).collect {
                when (it) {
                    is ResultState.Error -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }

    fun AddStudent(studentData: StudentData) {
        viewModelScope.launch {
            addStudentUseCase.addStudentdata(studentData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addStudentState.value = AddStudentScreenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _addStudentState.value = AddStudentScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addStudentState.value = AddStudentScreenState(studentdata = it.data)
                    }
                }
            }

        }

    }

    fun SignUp(userData: UserData) {
        viewModelScope.launch {
            signUPUseCase.SignUp(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _SignUpState.value = SignUpScrenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _SignUpState.value = SignUpScrenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _SignUpState.value = SignUpScrenState(userdata = it.data)
                    }
                }
            }

        }

    }
}

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userData: UserDataParent? = null
)

data class LoginScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userData: String? = null
)

data class SignUpScrenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userdata: String? = null
)

data class UploadUserProfileImageState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class AddStudentScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val studentdata: String? = null
)

data class GatePassScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val gatepassdata: String? = null
)

data class GetAllStudentsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val getallstudent: List<StudentData> = emptyList(),
)

data class AddMarksScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val addmarks: String? = null
)

data class AddAttenedanceScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val addattendance: String? = null
)

data class StudentLoginScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val studentData: String? = null
)

data class StudentProfileScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val studentdata: StudentDataParent? = null
)

data class StudentMarksScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val studentmarks: StudentDataParent? = null
)

