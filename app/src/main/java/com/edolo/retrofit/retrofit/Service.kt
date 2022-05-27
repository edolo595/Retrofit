package com.edolo.retrofit.retrofit

import android.util.Log
import com.edolo.retrofit.model.ErrorRequest
import com.edolo.retrofit.model.ErrorRequest2
import com.edolo.retrofit.model.ProductosM
import com.edolo.retrofit.network.ProductoApi
import okhttp3.ResponseBody
import retrofit2.Response

class Service {
    private val retrofit = RetrofitCliente.getClient()
    private val userApi = retrofit.create(ProductoApi::class.java)

    suspend fun successfulUsersResponse(): Response<List<ProductosM>> {

        val response = userApi.getAllProductos("", "")
        val successful = response.isSuccessful
        val httpStatusCode = response.code()
        val httpStatusMessage = response.message()
        val body = response.body()
        val errorBody: ResponseBody? = response.errorBody()

        /*  Log.i(
              "MENSAGE",
              "Error Body:${errorBody!!.string()}-Code:${httpStatusCode}- Mensage:${httpStatusMessage}"
          )

          return body ?: emptyList()
  */
        return response
    }

    suspend fun errorUsersResponse() {
        val response = userApi.getAllProductos("", "")
        val errorBody: ResponseBody? = response.errorBody()

    }

    fun headersUsersResponse() {

    }
}