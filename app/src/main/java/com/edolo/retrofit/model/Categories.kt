package com.edolo.retrofit.model

import java.io.Serializable

class AllCategories : ArrayList<Categories>()

data class Categories(
    val id: Int,
    val name: String,
    val image: String
):Serializable

data class CategoriesAgregar(
    val image: String = "",
    val name: String = ""
)