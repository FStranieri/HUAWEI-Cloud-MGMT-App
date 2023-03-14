package com.fs.hcmgmt.repository
import com.fs.hcmgmt.data.LoginResult
import com.fs.hcmgmt.util.Result

interface LoginRepository {
    suspend fun login(username: String, pw: String): Result<LoginResult>
    suspend fun loginIAM(username: String, usernameIAM: String, pw: String): Result<LoginResult>
    suspend fun logout(): Boolean
    suspend fun checkLoginStatus(): Boolean
}