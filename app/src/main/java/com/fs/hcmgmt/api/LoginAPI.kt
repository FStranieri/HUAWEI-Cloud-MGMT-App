package com.fs.hcmgmt.api

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url


interface LoginAPI {
    @POST("/v3/auth/tokens")
    fun loginIAM(
        @Body jsonBody: JSONObject
    ): Response<String>
}