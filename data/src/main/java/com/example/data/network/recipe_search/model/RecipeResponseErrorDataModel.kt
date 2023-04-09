package com.example.data.network.recipe_search.model

import com.google.gson.annotations.SerializedName

data class RecipeResponseErrorDataModel(
    @SerializedName("errorCode") private val errorCode: String,
    @SerializedName("message") private val message: String,
    @SerializedName("params") private val params: ArrayList<String>,
)
