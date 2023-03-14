package com.fs.hcmgmt.repository.datasource

import androidx.compose.ui.graphics.vector.addPathNodes
import com.fs.hcmgmt.api.LoginAPI
import com.fs.hcmgmt.data.LoginResult
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Response

class LoginDatasourceImpl(private val loginAPI: LoginAPI) : LoginDatasource {
    override suspend fun login(username: String, pw: String): Response<LoginResult> {
        TODO("Not yet implemented")
    }

    override suspend fun loginIAM(
        username: String,
        usernameIAM: String,
        pw: String
    ): Response<LoginResult> = loginAPI.loginIAM(JsonObject().apply {
        val domain = JsonObject().apply {
            addProperty("name", username)
        }
        val user = JsonObject().apply {
            add("domain", domain)
            addProperty("name", usernameIAM)
            addProperty("password", pw)
        }
        val password = JsonObject().apply {
            add("user", user)
        }
        val identity = JsonObject().apply {
            add("methods", JsonArray().apply {
                add("password")
            })
            add("password", password)
        }

        val auth = JsonObject().apply {
            add("identity", identity)
        }

        add("auth", auth)
    })
}