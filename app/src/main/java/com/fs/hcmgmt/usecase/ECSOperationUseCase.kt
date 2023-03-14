package com.fs.hcmgmt.usecase

import com.fs.hcmgmt.repository.ECSRepository
import com.fs.hcmgmt.util.ECSOperationMethod

class ECSOperationUseCase(private val ecsRepository: ECSRepository) {
    suspend operator fun invoke(projectId: String, idList: List<String>, method: ECSOperationMethod) =
        ecsRepository.operationECS(projectId, idList, method)
}