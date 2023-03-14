package com.fs.hcmgmt.repository.datasource

import com.fs.hcmgmt.util.ECSOperationMethod
import com.google.gson.JsonObject
import io.ktor.client.statement.*

interface ECSDatasource {
    suspend fun queryECS(): HttpResponse
    suspend fun getECSDetails(id: String): HttpResponse
    suspend fun operationECS(
        idList: List<String>,
        method: ECSOperationMethod
    ): HttpResponse
}