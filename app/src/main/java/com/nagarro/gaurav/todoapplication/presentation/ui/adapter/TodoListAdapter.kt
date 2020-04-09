package com.nagarro.gaurav.todoapplication.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nagarro.gaurav.todoapplication.BR
import com.nagarro.gaurav.todoapplication.R
import com.nagarro.gaurav.todoapplication.databinding.ItemTodoListBinding
import com.nagarro.gaurav.todoapplication.domain.model.TodoModel

class TodoListAdapter(private val todoList: List<TodoModel>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTodoListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_todo_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(todoList[position])
    }

    class ViewHolder(private val binding: ItemTodoListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.todoItem, data)
            binding.executePendingBindings()
        }
    }
}