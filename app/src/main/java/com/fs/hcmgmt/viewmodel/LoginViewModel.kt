package com.fs.hcmgmt.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.hcmgmt.data.LoginResult
import com.fs.hcmgmt.data.QueryECSResult
import com.fs.hcmgmt.usecase.LoginUseCase
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
    private val preferences: SharedPreferences
) : ViewModel() {

    private val mState = MutableStateFlow(value = LoginState())
    val state: StateFlow<LoginState>
        get() = mState

    init {
        updateState(state.value.copy(loggedIn = preferences.contains(Constants.PREF_TOKEN)))
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
        preferences.edit().remove(Constants.PREF_TOKEN).apply()
        updateState(state.value.copy(result = Result.Loading(), loggedIn = false))
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