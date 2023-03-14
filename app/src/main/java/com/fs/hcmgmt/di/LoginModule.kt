package com.fs.hcmgmt.di

import android.content.SharedPreferences
import com.fs.hcmgmt.api.LoginAPI
import com.fs.hcmgmt.repository.LoginRepository
import com.fs.hcmgmt.repository.LoginRepositoryImpl
import com.fs.hcmgmt.repository.datasource.LoginDatasource
import com.fs.hcmgmt.repository.datasource.LoginDatasourceImpl
import com.fs.hcmgmt.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    fun provideLoginDataSource(loginAPI: LoginAPI): LoginDatasource =
        LoginDatasourceImpl(loginAPI)

    @Provides
    fun provideLoginRepository(
        loginDatasource: LoginDatasource,
        sharedPreferences: SharedPreferences
    ): LoginRepository =
        LoginRepositoryImpl(loginDatasource, sharedPreferences)

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository) =
        LoginUseCase(loginRepository)
}