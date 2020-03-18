package com.nagarro.gaurav.todoapplication.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nagarro.gaurav.todoapplication.R

class ToDoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, TodoListFragment()).commit()
    }
}