package com.fs.hcmgmt.api

import com.google.gson.JsonObject
import io.ktor.client.statement.*


interface ECSAPI {
    suspend fun queryECS(projectId: String): HttpResponse
    suspend fun getECSDetails(projectId: String, id: String): HttpResponse
    suspend fun operationECS(projectId: String, body: JsonObject): HttpResponse
}