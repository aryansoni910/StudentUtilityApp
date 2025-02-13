package com.example.project_1.Domain.UseCase

import android.net.Uri
import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileImageUseCase @Inject constructor(private val repo: Repo)  {
    fun userProfileImage(uri: Uri) : Flow<ResultState<String>> {
        return repo.userProfileImage(uri)
    }
}

