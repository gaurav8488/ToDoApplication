package com.nagarro.gaurav.todoapplication.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nagarro.gaurav.todoapplication.R
import com.nagarro.gaurav.todoapplication.BR
import com.nagarro.gaurav.todoapplication.domain.repository.IDataRepository
import com.nagarro.gaurav.todoapplication.domain.usecase.UseCaseResult
import com.nagarro.gaurav.todoapplication.domain.model.TodoModel
import kotlinx.coroutines.*
import me.tatarka.bindingcollectionadapter2.ItemBinding
import kotlin.coroutines.CoroutineContext

class TodoListViewModel(private val dataRepository: IDataRepository) : ViewModel(), CoroutineScope {
    private val job = Job()
    val todoItems = MutableLiveData<List<TodoModel>>()
    val isShimmerGone = MutableLiveData<Boolean>(false)
    val isInternetGone = MutableLiveData<Boolean>()
    val todoItemsBinding: ItemBinding<TodoModel> = ItemBinding.of<TodoModel>(
        BR.todoItem, R.layout.item_todo_list
    )

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    fun getTodoList() {
        isShimmerGone.value = false
        isInternetGone.value = false
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getTodoList() }
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

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
