package com.fs.hcmgmt.api

import com.google.gson.JsonObject
import io.ktor.client.statement.*


interface ECSAPI {
    suspend fun queryECS(): HttpResponse
    suspend fun getECSDetails(id: String): HttpResponse
    suspend fun operationECS(body: JsonObject): HttpResponse
}