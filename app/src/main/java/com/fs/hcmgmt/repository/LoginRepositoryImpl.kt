package com.fs.hcmgmt.repository

import android.content.SharedPreferences
import com.fs.hcmgmt.data.LoginResult
import com.fs.hcmgmt.repository.datasource.LoginDatasource
import com.fs.hcmgmt.util.Constants
import com.fs.hcmgmt.util.Result
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

class LoginRepositoryImpl(
    private val loginDatasource: LoginDatasource,
    private val sharedPreferences: SharedPreferences
) :
    LoginRepository {
    override suspend fun login(username: String, pw: String) = responseToRequest(
        loginDatasource.login(username, pw)
    )

    override suspend fun loginIAM(username: String, usernameIAM: String, pw: String) =
        responseToRequest(
            loginDatasource.loginIAM(username, usernameIAM, pw)
        )

    override fun logout() {
        TODO("Not yet implemented")
    }

    private suspend fun responseToRequest(response: HttpResponse): Result<LoginResult> {
        if (response.status.isSuccess()) {
            val token: String = response.headers["X-Subject-Token"]!!
            sharedPreferences.edit().putString(Constants.PREF_TOKEN, token).apply()

            return Result.Success(response.body())
        }
        return Result.Error(response.bodyAsText())
    }
}