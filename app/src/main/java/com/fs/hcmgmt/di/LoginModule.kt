package com.fs.hcmgmt.di

import com.fs.hcmgmt.api.LoginAPI
import com.fs.hcmgmt.repository.LoginRepository
import com.fs.hcmgmt.repository.LoginRepositoryImpl
import com.fs.hcmgmt.repository.datasource.LoginDatasource
import com.fs.hcmgmt.repository.datasource.LoginDatasourceImpl
import com.fs.hcmgmt.usecase.CheckLoginStatusUseCase
import com.fs.hcmgmt.usecase.LoginUseCase
import com.fs.hcmgmt.usecase.LogoutUseCase
import com.fs.hcmgmt.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    fun provideLoginDataSource(loginAPI: LoginAPI, sessionManager: SessionManager): LoginDatasource =
        LoginDatasourceImpl(loginAPI, sessionManager)

    @Provides
    fun provideLoginRepository(
        loginDatasource: LoginDatasource,
        sessionManager: SessionManager
    ): LoginRepository =
        LoginRepositoryImpl(loginDatasource, sessionManager)

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository) =
        LoginUseCase(loginRepository)

    @Provides
    fun provideLogoutUseCase(loginRepository: LoginRepository) =
        LogoutUseCase(loginRepository)

    @Provides
    fun provideCheckLoginStatusUseCase(loginRepository: LoginRepository) =
        CheckLoginStatusUseCase(loginRepository)
}