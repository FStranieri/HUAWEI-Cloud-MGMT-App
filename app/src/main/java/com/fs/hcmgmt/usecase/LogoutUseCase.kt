package com.fs.hcmgmt.usecase

import com.fs.hcmgmt.repository.LoginRepository

class LogoutUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(): Boolean = loginRepository.logout()
}