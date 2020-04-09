package com.nagarro.gaurav.todoapplication.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagarro.gaurav.todoapplication.domain.model.TodoModel
import com.nagarro.gaurav.todoapplication.domain.repository.IDataRepository
import com.nagarro.gaurav.todoapplication.domain.usecase.UseCaseResult
import kotlinx.coroutines.launch

class TodoListViewModel(private val dataRepository: IDataRepository) : ViewModel() {
    val todoItems = MutableLiveData<List<TodoModel>>()
    val isShimmerGone = MutableLiveData<Boolean>(false)
    val isInternetGone = MutableLiveData<Boolean>()

    /**
     * Function to get items from web API
     */
    fun getTodoList() {
        isShimmerGone.value = false
        isInternetGone.value = false
        viewModelScope.launch {
            val result = dataRepository.getTodoList()
            isShimmerGone.value = true
            when (result) {
                is UseCaseResult.Success -> {
                    todoItems.value = result.data
                    isInternetGone.value = false
                }
                is UseCaseResult.Error ->
                    isInternetGone.value = true
            }
        }
    }
}
