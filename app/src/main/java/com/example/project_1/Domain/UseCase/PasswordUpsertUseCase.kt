package com.example.project_1.Domain.UseCase

import com.example.project_1.Data.Database.PasswordManager
import com.example.project_1.Domain.Repo.Repo
import javax.inject.Inject

class PasswordUpsertUseCase @Inject constructor(val repo: Repo) {
    suspend fun PasswordUpssert(passwordManager: PasswordManager){
        return repo.upsert(
            passwordManager = passwordManager)
    }
}