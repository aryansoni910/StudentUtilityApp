package com.example.project_1.Domain.UseCase

import com.example.project_1.Data.Database.PasswordManager
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPasswordUseCase @Inject constructor(val repo: Repo) {
    fun getpassword():Flow<List<PasswordManager>>{
        return repo.getAllPassword()
    }
}