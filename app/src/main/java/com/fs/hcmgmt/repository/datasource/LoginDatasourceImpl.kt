package com.fs.hcmgmt.repository.datasource

import com.fs.hcmgmt.api.LoginAPI
import org.json.JSONObject
import retrofit2.Response

class LoginDatasourceImpl(private val loginAPI: LoginAPI) : LoginDatasource {
    override suspend fun login(username: String, pw: String): Response<String> {
        TODO("Not yet implemented")
    }

    override suspend fun loginIAM(
        username: String,
        usernameIAM: String,
        pw: String
    ): Response<String> = loginAPI.loginIAM (JSONObject().apply{
        val domain = JSONObject().apply {
            put("name", username)
        }
        val user = JSONObject().apply {
            put("domain", domain)
            put("name", usernameIAM)
            put("password", pw)
        }
        val password = JSONObject().apply {
            put("user", user)
        }
        val identity = JSONObject().apply {
            put("methods", listOf("password"))
            put("password", password)
        }

        val auth = JSONObject().apply {
            put("identity", identity)
        }

        put("auth", auth)
    })
}