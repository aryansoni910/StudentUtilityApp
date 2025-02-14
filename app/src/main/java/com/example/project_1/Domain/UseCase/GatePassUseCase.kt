package com.example.project_1.Domain.UseCase

import com.example.project_1.Common.ResultState
import com.example.project_1.Domain.Model.GatePassdata
import com.example.project_1.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GatePassUseCase @Inject constructor(private val repo: Repo) {
    fun gatepass(gatePassdata: GatePassdata):Flow<ResultState<String>>{
        return repo.gatepass(gatePassdata)
    }
}