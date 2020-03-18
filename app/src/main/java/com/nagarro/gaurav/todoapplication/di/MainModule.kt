package com.nagarro.gaurav.todoapplication.di

import android.content.Context
import com.nagarro.gaurav.todoapplication.BuildConfig
import com.nagarro.gaurav.todoapplication.data.repository.DataRepositoryImpl
import com.nagarro.gaurav.todoapplication.domain.repository.IDataRepository
import com.nagarro.gaurav.todoapplication.data.source.NetWorkApi
import com.nagarro.gaurav.todoapplication.data.source.NetworkConnectionInterceptor
import com.nagarro.gaurav.todoapplication.presentation.viewmodel.TodoListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val mainModule = module {
    single { DataRepositoryImpl(get()) as IDataRepository }
    viewModel {
        TodoListViewModel(
            get()
        )
    }
    factory { provideOkHttpClient(get(), androidContext()) }
    factory { provideApi(get()) }
    factory { httpLoggingInterceptor() }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(
    authInterceptor: HttpLoggingInterceptor,
    androidContext: Context
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(authInterceptor)
        .addInterceptor(NetworkConnectionInterceptor(androidContext))
        .build()
}

fun provideApi(retrofit: Retrofit): NetWorkApi = retrofit.create(NetWorkApi::class.java)

fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}


