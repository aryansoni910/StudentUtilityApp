package com.example.project_1.Presentation.ViewModel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.Common.ApiKey
import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.ChatBotEnum
import com.example.project_1.Domain.Model.GatePassdata
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.Subject
import com.example.project_1.Domain.Model.SubjectDataParent
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Model.UserDataParent
import com.example.project_1.Domain.Model.chatbotData
import com.example.project_1.Domain.UseCase.AddMarks5UseCase
import com.example.project_1.Domain.UseCase.AddStudentUseCase
import com.example.project_1.Domain.UseCase.GatePassUseCase
import com.example.project_1.Domain.UseCase.GetAllStudents5UseCase
import com.example.project_1.Domain.UseCase.LoginUserUseCase
import com.example.project_1.Domain.UseCase.ProfileScreenUsecase
import com.example.project_1.Domain.UseCase.SignUPUseCase
import com.example.project_1.Domain.UseCase.UserProfileImageUseCase
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
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
    private val addMarks5UseCase: AddMarks5UseCase
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


    private val genAI by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = ApiKey
        )
    }
    val list by lazy {
        mutableStateListOf<chatbotData>()
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