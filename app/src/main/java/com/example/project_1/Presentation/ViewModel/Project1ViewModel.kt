package com.example.project_1.Presentation.ViewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Model.UserDataParent
import com.example.project_1.Domain.UseCase.AddStudentUseCase
import com.example.project_1.Domain.UseCase.LoginUserUseCase
import com.example.project_1.Domain.UseCase.ProfileScreenUsecase
import com.example.project_1.Domain.UseCase.SignUPUseCase
import com.example.project_1.Domain.UseCase.UserProfileImageUseCase
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
    private val addStudentUseCase: AddStudentUseCase
) : ViewModel() {
    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _SignUpState = MutableStateFlow(SignUpScrenState())
    val SignUpState = _SignUpState.asStateFlow()

    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileStateScreen = _profileScreenState.asStateFlow()

    private val _userProfileImageState = MutableStateFlow((UploadUserProfileImageState()))
    val userProfileImageState =_userProfileImageState.asStateFlow()

    private val _addStudentState = MutableStateFlow(AddStudentScreenState())
    val addStudentState = _addStudentState.asStateFlow()

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

    fun getuserById(uid: String) {
        viewModelScope.launch {
            profileScreenUsecase.getuserById(uid).collect{
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
    val userData: UserDataParent ?= null
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