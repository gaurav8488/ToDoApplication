package com.nagarro.gaurav.todoapplication.domain.model

data class TodoModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)