package com.sukacolab.app.data.source.network

sealed class BaseResult<out R> private constructor() {
    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Error(val error: String) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}
