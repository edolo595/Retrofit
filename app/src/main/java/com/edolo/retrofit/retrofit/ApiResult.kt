package com.edolo.retrofit.retrofit

sealed class ApiResult<out T>(
    val status: ApiStatus,
    val data: T?,
    val message: String?,
    val loading: Boolean,
    val internet: Boolean = false
) {

    data class Success<out R>(val _data: R?) : ApiResult<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null,
        loading = false
    )

    data class Error(val exception: String) : ApiResult<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        message = exception,
        loading = false
    )

    data class Loading<out R>(val _data: R?, val isLoading: Boolean) : ApiResult<R>(
        status = ApiStatus.LOADING,
        data = _data,
        message = null,
        loading = true
    )

    data class Internet(val interne: Boolean) : ApiResult<Nothing>(
        status = ApiStatus.INTERNET,
        data = null,
        message = null,
        loading = false,
        internet = interne
    )
}
