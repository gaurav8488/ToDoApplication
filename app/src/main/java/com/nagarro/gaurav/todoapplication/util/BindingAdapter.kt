package com.nagarro.gaurav.todoapplication.util

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:visibility")
    fun visibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}