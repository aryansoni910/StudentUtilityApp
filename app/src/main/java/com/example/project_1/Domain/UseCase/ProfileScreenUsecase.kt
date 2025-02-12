package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.UserDataParent
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileScreenUsecase @Inject constructor(val repo: Repo) {

    fun getuserById(uid:String) : Flow<ResultState<UserDataParent>> {
        return repo.getuserById(uid)
    }
}
