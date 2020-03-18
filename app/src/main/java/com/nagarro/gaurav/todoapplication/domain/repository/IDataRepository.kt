package com.nagarro.gaurav.todoapplication.domain.repository

import com.nagarro.gaurav.todoapplication.domain.usecase.UseCaseResult
import com.nagarro.gaurav.todoapplication.domain.model.TodoModel

interface IDataRepository {
    suspend fun getTodoList(): UseCaseResult<List<TodoModel>>
}