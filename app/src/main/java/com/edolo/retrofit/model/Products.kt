package com.edolo.retrofit.model

/**
 * ResponseBody: ResponseBody es la data class que obtiene la respuesta de la api
 * despues de haber insertado,
 * De lo contrario regresara un errorRequest
 *
 * */


data class ProductosM(
    val images: List<String>?,
    val price: Long = 0,
    val description: String = "",
    val id: Int = 0,
    val title: String = "",
    val category: Categories,
    val categoryId: Int = 0
)

data class ProductosUpdate(
    val price: Int = 0,
    val title: String = "",
    val description: String = "",
    val categoryId: Int = 0,
    val images: List<String>?,
)

