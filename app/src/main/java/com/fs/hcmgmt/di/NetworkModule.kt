package com.fs.hcmgmt.di

import com.fs.hcmgmt.api.ECSAPI
import com.fs.hcmgmt.api.LoginAPI
import com.fs.hcmgmt.util.Constants
import com.fs.hcmgmt.util.SessionManager
import com.google.gson.JsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorInstance(): HttpClient {
        return HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    setLenient()
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideLoginApi(httpClient: HttpClient): LoginAPI {
        return object : LoginAPI {
            override suspend fun loginIAM(body: JsonObject): HttpResponse {
                return httpClient.post("https://iam.myhuaweicloud.eu/v3/auth/tokens") {
                    parameter("nocatalog", true)
                    header(HttpHeaders.ContentType, "application/json; charset=UTF-8")
                    setBody(body)
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideQueryECSApi(httpClient: HttpClient, sessionManager: SessionManager): ECSAPI {
        return object : ECSAPI {
            override suspend fun queryECS(): HttpResponse {
                return httpClient.get("https://ecs.${sessionManager.getZone()}.myhuaweicloud.eu/v1/${sessionManager.getProject()}/cloudservers/detail") {
                    headers {
                        append(HttpHeaders.ContentType, "application/json")
                        append(
                            Constants.HEADER_TOKEN, sessionManager.getToken() ?: ""
                        )
                    }
                }
            }

            override suspend fun getECSDetails(id: String): HttpResponse {
                TODO("Not yet implemented")
            }

            override suspend fun operationECS(body: JsonObject): HttpResponse {
                return httpClient.post("https://ecs.${sessionManager.getZone()}.myhuaweicloud.eu/v1/${sessionManager.getProject()}/cloudservers/action") {
                    headers {
                        append(HttpHeaders.ContentType, "application/json")
                        append(
                            Constants.HEADER_TOKEN, sessionManager.getToken() ?: ""
                        )
                    }
                    setBody(body)
                }
            }
        }
    }
}