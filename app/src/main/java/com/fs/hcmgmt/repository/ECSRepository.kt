package com.fs.hcmgmt.repository

import com.fs.hcmgmt.data.ECSTaskResult
import com.fs.hcmgmt.data.QueryECSResult
import com.fs.hcmgmt.util.ECSOperationMethod
import com.fs.hcmgmt.util.Result
import com.google.gson.JsonObject

interface ECSRepository {
    suspend fun queryECS(projectId: String): Result<QueryECSResult>
    suspend fun operationECS(
        projectId: String,
        idList: List<String>,
        method: ECSOperationMethod
    ): Result<ECSTaskResult>
}