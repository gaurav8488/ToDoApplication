package com.nagarro.gaurav.todoapplication.data.repository

import com.nagarro.gaurav.todoapplication.data.source.NetWorkApi
import com.nagarro.gaurav.todoapplication.domain.model.TodoModel
import com.nagarro.gaurav.todoapplication.domain.repository.IDataRepository
import com.nagarro.gaurav.todoapplication.domain.usecase.UseCaseResult

class DataRepositoryImpl(private val networkApi: NetWorkApi) :
    IDataRepository {
    override suspend fun getTodoList(): UseCaseResult<List<TodoModel>> {
        return try {
            val result = networkApi.getTodoListData()
            if (result.isSuccessful)
                UseCaseResult.Success(result.body() as List<TodoModel>)
            else UseCaseResult.Error(RuntimeException("test error"))
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

