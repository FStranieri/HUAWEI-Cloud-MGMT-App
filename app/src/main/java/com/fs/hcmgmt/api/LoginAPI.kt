package com.fs.hcmgmt.api

import com.fs.hcmgmt.data.LoginResult
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginAPI {
    @POST("/v3/auth/tokens")
    suspend fun loginIAM(
        @Body jsonBody: JsonObject
    ): Response<LoginResult>
}