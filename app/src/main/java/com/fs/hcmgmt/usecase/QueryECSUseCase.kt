package com.fs.hcmgmt.usecase

import com.fs.hcmgmt.repository.ECSRepository

class QueryECSUseCase(private val ecsRepository: ECSRepository) {
    suspend operator fun invoke() =
        ecsRepository.queryECS()
}