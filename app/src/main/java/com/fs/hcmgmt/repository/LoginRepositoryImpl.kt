package com.fs.hcmgmt.repository

import com.fs.hcmgmt.data.LoginResult
import com.fs.hcmgmt.repository.datasource.LoginDatasource
import com.fs.hcmgmt.util.Result
import com.fs.hcmgmt.util.SessionManager
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

class LoginRepositoryImpl(
    private val loginDatasource: LoginDatasource,
    private val sessionManager: SessionManager
) :
    LoginRepository {
    override suspend fun login(username: String, pw: String) = responseToRequest(
        loginDatasource.login(username, pw)
    )

    override suspend fun loginIAM(username: String, usernameIAM: String, pw: String) =
        responseToRequest(
            loginDatasource.loginIAM(username, usernameIAM, pw)
        )

    override suspend fun logout(): Boolean = sessionManager.clearToken()

    private suspend fun responseToRequest(response: HttpResponse): Result<LoginResult> {
        if (response.status.isSuccess()) {
            val token: String = response.headers["X-Subject-Token"]!!
            sessionManager.putToken(token)
            val result: Result<LoginResult> = Result.Success(response.body())
            result.data?.let {
                sessionManager.putProject(it.token.project.id)
                sessionManager.putZone(it.token.project.name)
            }
            return result
        }
        return Result.Error(response.bodyAsText())
    }

    override suspend fun checkLoginStatus(): Boolean = sessionManager.hasToken()
}