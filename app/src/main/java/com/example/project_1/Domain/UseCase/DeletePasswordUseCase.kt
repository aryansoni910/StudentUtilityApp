package com.example.project_1.Domain.UseCase

import com.example.project_1.Data.Database.PasswordManager
import com.example.project_1.Domain.Repo.Repo
import javax.inject.Inject

class DeletePasswordUseCase @Inject constructor(val repo: Repo) {
    suspend fun PasswordDelete(passwordManager: PasswordManager){
        return repo.delete(
            passwordManager = passwordManager)
    }
}