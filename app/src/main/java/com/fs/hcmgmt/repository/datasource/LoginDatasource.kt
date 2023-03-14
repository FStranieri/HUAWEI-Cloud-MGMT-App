package com.fs.hcmgmt.repository.datasource

import io.ktor.client.statement.*

interface LoginDatasource {
    suspend fun login(username: String, pw: String): HttpResponse
    suspend fun loginIAM(username: String, usernameIAM: String, pw: String): HttpResponse
}