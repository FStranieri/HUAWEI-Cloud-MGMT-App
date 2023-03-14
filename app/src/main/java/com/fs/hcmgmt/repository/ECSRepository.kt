package com.fs.hcmgmt.repository

import com.fs.hcmgmt.data.ECSTaskResult
import com.fs.hcmgmt.data.QueryECSResult
import com.fs.hcmgmt.util.ECSOperationMethod
import com.fs.hcmgmt.util.Result

interface ECSRepository {
    suspend fun queryECS(): Result<QueryECSResult>
    suspend fun operationECS(
        idList: List<String>,
        method: ECSOperationMethod
    ): Result<ECSTaskResult>
}