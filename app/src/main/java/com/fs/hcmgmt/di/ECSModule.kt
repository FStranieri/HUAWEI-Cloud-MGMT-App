package com.fs.hcmgmt.di

import com.fs.hcmgmt.api.ECSAPI
import com.fs.hcmgmt.repository.ECSRepository
import com.fs.hcmgmt.repository.ECSRepositoryImpl
import com.fs.hcmgmt.repository.datasource.ECSDatasource
import com.fs.hcmgmt.repository.datasource.ECSDatasourceImpl
import com.fs.hcmgmt.usecase.ECSOperationUseCase
import com.fs.hcmgmt.usecase.QueryECSUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ECSModule {
    @Provides
    fun provideECSDataSource(ecsapi: ECSAPI) : ECSDatasource =
        ECSDatasourceImpl(ecsapi)

    @Provides
    fun provideECSRepository(ecsDatasource: ECSDatasource): ECSRepository =
        ECSRepositoryImpl(ecsDatasource)

    @Provides
    fun provideQueryECSUseCase(ecsRepository: ECSRepository) =
        QueryECSUseCase(ecsRepository)

    @Provides
    fun provideOperationECSUseCase(ecsRepository: ECSRepository) =
        ECSOperationUseCase(ecsRepository)
}