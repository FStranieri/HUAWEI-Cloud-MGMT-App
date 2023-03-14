package com.fs.hcmgmt.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.hcmgmt.data.ECSTaskResult
import com.fs.hcmgmt.data.QueryECSResult
import com.fs.hcmgmt.usecase.ECSOperationUseCase
import com.fs.hcmgmt.usecase.QueryECSUseCase
import com.fs.hcmgmt.util.ECSOperationMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.fs.hcmgmt.util.Result
import javax.inject.Inject

@HiltViewModel
class ECSViewModel @Inject constructor(
    private val ecsQueryListUseCase: QueryECSUseCase,
    private val ecsOperationUseCase: ECSOperationUseCase
) : ViewModel() {

    private val mState = MutableStateFlow(value = ECSState())
    val state: StateFlow<ECSState>
        get() = mState

    init {

    }

    private fun updateState(newState: ECSState) {
        viewModelScope.launch {
            mState.emit(value = newState)
        }
    }

    fun queryECS() {
        viewModelScope.launch {
            mState.emit(
                value = state.value.copy(
                    queryResult = ecsQueryListUseCase.invoke(
                        "f90b2c5c1c1a42e38215a9edd0b37cf0"
                    ),
                    isRefreshing = false
                )
            )
        }
    }

    fun operationECS(idList: List<String>, method: ECSOperationMethod) {
        viewModelScope.launch {
            mState.emit(
                value = state.value.copy(
                    operationResult = ecsOperationUseCase.invoke(
                        "f90b2c5c1c1a42e38215a9edd0b37cf0",
                        idList,
                        method
                    )
                )
            )
        }
    }

    fun setRefreshStatus(isRefreshing: Boolean) {
        updateState(state.value.copy(isRefreshing = isRefreshing))
    }

    fun resetFailureOutput() {
        updateState(state.value.copy(failureOutput = null))
    }

    companion object {
        const val TAG = "ECSViewModel"
    }

    data class ECSState(
        var isRefreshing: Boolean = false,
        val queryResult: Result<QueryECSResult> = Result.Loading(),
        val operationResult: Result<ECSTaskResult> = Result.Loading(),
        val failureOutput: Exception? = null
    )
}