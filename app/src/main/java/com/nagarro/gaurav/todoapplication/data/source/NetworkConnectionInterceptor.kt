package com.nagarro.gaurav.todoapplication.data.source

import android.content.Context
import android.net.ConnectivityManager
import com.nagarro.gaurav.todoapplication.util.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor(private val mContext: Context) : Interceptor {
    private val isConnected: Boolean
        get() {
            val connectivityManager =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}