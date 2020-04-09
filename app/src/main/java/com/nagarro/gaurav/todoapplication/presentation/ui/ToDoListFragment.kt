package com.nagarro.gaurav.todoapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nagarro.gaurav.todoapplication.R
import com.nagarro.gaurav.todoapplication.databinding.FragmentTodoListBinding
import com.nagarro.gaurav.todoapplication.presentation.ui.adapter.TodoListAdapter
import com.nagarro.gaurav.todoapplication.presentation.viewmodel.TodoListViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class TodoListFragment : Fragment() {
    lateinit var binding: FragmentTodoListBinding
    private val todoListModel: TodoListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        binding.viewModel=todoListModel
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoListModel.getTodoList()
    }

    override fun onStart() {
        super.onStart()
        todoListModel.todoItems.observe(this, Observer {
            it?.let {
                binding.recyclerView.adapter = TodoListAdapter(it)
            }
        })

        todoListModel.isShimmerGone.observe(this, Observer { isShimmerGone ->
            if(isShimmerGone) stopShimmer() else startShimmer()
        })
    }

    override fun onResume() {
        super.onResume()
        startShimmer()
     }

    override fun onPause() {
        super.onPause()
        stopShimmer()
    }

    private fun startShimmer() {
        binding.shimmerViewContainer.startShimmerAnimation()
        binding.shimmerViewContainer.visibility = View.VISIBLE
    }

    private fun stopShimmer() {
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.visibility = View.GONE
    }
}
