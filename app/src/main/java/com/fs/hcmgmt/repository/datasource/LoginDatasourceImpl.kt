package com.fs.hcmgmt.repository.datasource

import com.fs.hcmgmt.api.LoginAPI
import com.fs.hcmgmt.util.SessionManager
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.ktor.client.statement.*

class LoginDatasourceImpl(
    private val loginAPI: LoginAPI,
    private val sessionManager: SessionManager
) : LoginDatasource {
    override suspend fun login(username: String, pw: String): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun loginIAM(
        username: String,
        usernameIAM: String,
        pw: String
    ): HttpResponse = loginAPI.loginIAM(JsonObject().apply {
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

        val project = JsonObject().apply {
            addProperty("name", sessionManager.getZone())
        }

        val scope = JsonObject().apply {
            add("project", project)
        }

        val auth = JsonObject().apply {
            add("identity", identity)
            add("scope", scope)
        }

        add("auth", auth)
    })
}