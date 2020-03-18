package com.nagarro.gaurav.todoapplication

import android.app.Application
import com.nagarro.gaurav.todoapplication.di.mainModule
import org.koin.android.ext.android.startKoin

class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this,
            listOf(mainModule))
    }
}