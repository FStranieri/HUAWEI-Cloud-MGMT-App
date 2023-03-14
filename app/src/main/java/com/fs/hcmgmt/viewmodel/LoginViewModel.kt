package com.fs.hcmgmt.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.hcmgmt.data.LoginResult
import com.fs.hcmgmt.data.QueryECSResult
import com.fs.hcmgmt.usecase.CheckLoginStatusUseCase
import com.fs.hcmgmt.usecase.LoginUseCase
import com.fs.hcmgmt.usecase.LogoutUseCase
import com.fs.hcmgmt.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.fs.hcmgmt.util.Result
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase
) : ViewModel() {

    private val mState = MutableStateFlow(value = LoginState())
    val state: StateFlow<LoginState>
        get() = mState

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            mState.emit(
                value = state.value.copy(
                    result = Result.Loading(),
                    loggedIn = checkLoginStatusUseCase.invoke()
                )
            )
        }
    }

    fun switchUI(loginAsIAM: Boolean) {
        updateState(state.value.copy(loginAsIAM = loginAsIAM))
    }

    private fun updateState(newState: LoginState) {
        viewModelScope.launch {
            mState.emit(value = newState)
        }
    }

    fun login(username: String, pw: String) {
        viewModelScope.launch {
            mState.emit(value = state.value.copy(result = loginUseCase.invoke(username, pw)))
        }
    }

    fun loginIAM(username: String, usernameIAM: String, pw: String) {
        viewModelScope.launch {
            mState.emit(
                value = state.value.copy(
                    result = loginUseCase.invoke(
                        username,
                        usernameIAM,
                        pw
                    ),
                    loggedIn = true
                )
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            mState.emit(
                value = state.value.copy(
                    result = Result.Loading(),
                    loggedIn = logoutUseCase.invoke()
                )
            )
        }
    }

    fun resetFailureOutput() {
        updateState(state.value.copy(failureOutput = null))
    }

    companion object {
        const val TAG = "LoginViewModel"
    }

    data class LoginState(
        val loginAsIAM: Boolean = false,
        val result: Result<LoginResult> = Result.Loading(),
        val ecs: Result<QueryECSResult> = Result.Loading(),
        val failureOutput: Exception? = null,
        val loggedIn: Boolean = false
    )
}