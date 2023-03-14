package com.fs.hcmgmt.repository.datasource

import com.fs.hcmgmt.data.LoginResult
import retrofit2.Response

interface LoginDatasource {
    suspend fun login(username: String, pw: String): Response<LoginResult>
    suspend fun loginIAM(username: String, usernameIAM: String, pw: String): Response<LoginResult>
}