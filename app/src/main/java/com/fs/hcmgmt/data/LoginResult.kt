package com.fs.hcmgmt.data

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @SerializedName("token")
    val token: Token
)

data class Token (
    val catalog: List<Any?>,

    @SerializedName("expires_at")
    val expiresAt: String,

    @SerializedName("issued_at")
    val issuedAt: String,

    val methods: List<String>,
    val project: Project,
    val roles: List<Domain>,
    val user: Project
)

data class Project (
    val domain: Domain,
    val id: String,
    val name: String,

    @SerializedName("password_expires_at")
    val passwordExpiresAt: String? = null
)

data class Domain (
    val id: String,
    val name: String
)
