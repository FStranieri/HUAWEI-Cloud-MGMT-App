package com.fs.hcmgmt.usecase

import com.fs.hcmgmt.repository.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(username: String, pw: String) =
        loginRepository.login(username, pw)
    suspend operator fun invoke(username: String, usernameIAM: String, pw: String) =
        loginRepository.loginIAM(username, usernameIAM, pw)
}