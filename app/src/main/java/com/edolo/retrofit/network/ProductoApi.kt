package com.edolo.retrofit.network

import com.edolo.retrofit.model.ProductosM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductoApi {

    /**
     * funcion para obtener todos los productos de la api
     * limitar nuestro archivo a limit y offset
     */
    @GET("products")
    suspend fun getAllProductos(
        @Query("products") limite: String,
        @Query("limit") offset: String
    ): Response<List<ProductosM>>
}