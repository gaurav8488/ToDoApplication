package com.nagarro.gaurav.todoapplication.data.source

import com.nagarro.gaurav.todoapplication.domain.model.TodoModel
import retrofit2.Response
import retrofit2.http.GET

interface NetWorkApi{
    @GET("/todos")
    suspend fun getTodoListData(): Response<List<TodoModel>>
}