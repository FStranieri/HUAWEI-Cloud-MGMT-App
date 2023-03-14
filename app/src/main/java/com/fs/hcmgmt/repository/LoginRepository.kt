package com.fs.hcmgmt.repository
import com.fs.hcmgmt.util.Result

interface LoginRepository {
    suspend fun login(username: String, pw: String): Result<String>
    suspend fun loginIAM(username: String, usernameIAM: String, pw: String): Result<String>
    fun logout()
}