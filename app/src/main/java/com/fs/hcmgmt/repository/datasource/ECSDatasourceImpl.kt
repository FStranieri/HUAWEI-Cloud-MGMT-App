package com.fs.hcmgmt.repository.datasource

import com.fs.hcmgmt.api.ECSAPI
import com.fs.hcmgmt.util.ECSOperationMethod
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.ktor.client.statement.*

class ECSDatasourceImpl(private val ecsApi: ECSAPI) : ECSDatasource {
    override suspend fun queryECS(projectId: String): HttpResponse = ecsApi.queryECS(projectId)

    override suspend fun getECSDetails(projectId: String, id: String): HttpResponse =
        ecsApi.getECSDetails(projectId, id)

    override suspend fun operationECS(
        projectId: String,
        idList: List<String>,
        method: ECSOperationMethod
    ): HttpResponse =
        ecsApi.operationECS(projectId, JsonObject().apply {
            val servers = JsonArray().apply {
                idList.forEach { id ->
                    add(JsonObject().apply {
                        addProperty("id", id)
                    })
                }
            }

            val osMethod = JsonObject().apply {
                if (method == ECSOperationMethod.STOP || method == ECSOperationMethod.RESTART) {
                    addProperty("type", method.type)
                }

                add("servers", servers)
            }

            add(method.osName, osMethod)
        })
}