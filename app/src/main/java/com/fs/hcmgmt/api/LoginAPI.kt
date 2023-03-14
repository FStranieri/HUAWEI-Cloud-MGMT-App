package com.fs.hcmgmt.api

import com.google.gson.JsonObject
import io.ktor.client.statement.*


interface LoginAPI {
    suspend fun loginIAM(body: JsonObject): HttpResponse
}