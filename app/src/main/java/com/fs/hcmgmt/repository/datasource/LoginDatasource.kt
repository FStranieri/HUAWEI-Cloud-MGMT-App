package com.fs.hcmgmt.repository.datasource

import retrofit2.Response

interface LoginDatasource {
    suspend fun login(username: String, pw: String): Response<String>
    suspend fun loginIAM(username: String, usernameIAM: String, pw: String): Response<String>
}