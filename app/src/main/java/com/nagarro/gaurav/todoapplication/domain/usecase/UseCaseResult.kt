package com.nagarro.gaurav.todoapplication.domain.usecase

sealed class UseCaseResult<T : Any> {
    class Success<T : Any>(val data: T) : UseCaseResult<T>()
    class Error<T : Any>(val exception: Throwable) : UseCaseResult<T>()
}