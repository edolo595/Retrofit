package com.edolo.retrofit.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.edolo.retrofit.clases.ConnectionLiveData
import com.edolo.retrofit.retrofit.ApiResult
import com.edolo.retrofit.retrofit.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainActivityVM() : ViewModel() {

    private val instancia = Service()

    private fun getDatos(context: Context) = flow {
        //      emit(ApiResult.Internet(true))
        emit(ApiResult.Loading("", true))
        val response = instancia.successfulUsersResponse()
        if (response.isSuccessful) {
            emit(ApiResult.Success(response.body()))
        } else {
            val errroMsg = response.errorBody()?.string()
            response.errorBody()?.close()
            emit(ApiResult.Error(errroMsg!!))
        }

        //emit(response)
    }.flowOn(Dispatchers.IO)

    fun getData(context: Context) = getDatos(context)
        .catch {
            // flow {
            emit(ApiResult.Error("Ocorruio un error"))
            // }.flowOn(Dispatchers.IO)
        }
        .asLiveData()

    fun getInternet(context: Context): LiveData<Boolean> = ConnectionLiveData(context)

}