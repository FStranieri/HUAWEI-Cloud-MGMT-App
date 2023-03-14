package com.fs.hcmgmt.repository.datasource

import com.fs.hcmgmt.util.ECSOperationMethod
import com.google.gson.JsonObject
import io.ktor.client.statement.*

interface ECSDatasource {
    suspend fun queryECS(projectId: String): HttpResponse
    suspend fun getECSDetails(projectId: String, id: String): HttpResponse
    suspend fun operationECS(
        projectId: String,
        idList: List<String>,
        method: ECSOperationMethod
    ): HttpResponse
}