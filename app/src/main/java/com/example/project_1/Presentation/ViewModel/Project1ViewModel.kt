package com.example.project_1.Presentation.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.UseCase.LoginUserUseCase
import com.example.project_1.Domain.UseCase.SignUPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class Project1ViewModel @Inject constructor(private val loginUserUseCase: LoginUserUseCase,private val signUPUseCase: SignUPUseCase) : ViewModel() {
    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _SignUpState = MutableStateFlow(SignUpScrenState())
    val SignUpState  = _SignUpState.asStateFlow()

    fun login(userData: UserData){
        viewModelScope.launch {
            loginUserUseCase.loginUser(userData).collect{
             when(it){
                 is ResultState.Error -> _loginScreenState.value =LoginScreenState(error = it.message)
                 ResultState.Loading ->  _loginScreenState.value =LoginScreenState(isLoading = true)
                 is ResultState.Success ->  _loginScreenState.value = LoginScreenState(userData = it.data)

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
data class LoginScreenState(
    val isLoading : Boolean = false,
    val error : String?= null,
    val userData:String?= null)

data class SignUpScrenState(
    val isLoading : Boolean = false,
    val error : String?= null,
    val userdata:String?= null
)