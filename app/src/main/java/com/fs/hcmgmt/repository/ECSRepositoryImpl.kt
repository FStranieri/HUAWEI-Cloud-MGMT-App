package com.fs.hcmgmt.repository

import com.fs.hcmgmt.data.ECSTaskResult
import com.fs.hcmgmt.data.QueryECSResult
import com.fs.hcmgmt.repository.datasource.ECSDatasource
import com.fs.hcmgmt.util.ECSOperationMethod
import com.fs.hcmgmt.util.Result
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ECSRepositoryImpl(private val ecsDatasource: ECSDatasource) :
    ECSRepository {

    override suspend fun queryECS(projectId: String): Result<QueryECSResult> {
        return parseECSQueryListResponse(
            ecsDatasource.queryECS(projectId)
        )
    }

    override suspend fun operationECS(
        projectId: String,
        idList: List<String>,
        method: ECSOperationMethod
    ): Result<ECSTaskResult> {
        return parseECSTaskResponse(
            ecsDatasource.operationECS(projectId, idList, method)
        )
    }

    private suspend fun parseECSQueryListResponse(response: HttpResponse): Result<QueryECSResult> {
        if (response.status.isSuccess()) {
            return Result.Success(response.body())
        }
        return Result.Error(response.bodyAsText())
    }

    private suspend fun parseECSTaskResponse(response: HttpResponse): Result<ECSTaskResult> {
        if (response.status.isSuccess()) {
            return Result.Success(response.body())
        }
        return Result.Error(response.bodyAsText())
    }
}