package com.fs.hcmgmt.repository

import com.fs.hcmgmt.repository.datasource.LoginDatasource
import retrofit2.Response
import com.fs.hcmgmt.util.Result

class LoginRepositoryImpl(private val loginDatasource: LoginDatasource) :
    LoginRepository {
    override suspend fun login(username: String, pw: String) =  responseToRequest(
        loginDatasource.login(username, pw)
    )

    override suspend fun loginIAM(username: String, usernameIAM: String, pw: String) =
        responseToRequest(
            loginDatasource.loginIAM(username, usernameIAM, pw)
        )

    override fun logout() {
        TODO("Not yet implemented")
    }

    private fun responseToRequest(response: Response<String>): Result<String> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Result.Success(result)
            }
        }
        return Result.Error(response.message())
    }
}