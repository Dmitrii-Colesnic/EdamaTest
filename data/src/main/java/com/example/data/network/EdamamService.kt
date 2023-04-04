package com.example.data.network

import retrofit2.Response
import retrofit2.http.GET

interface EdamamService {

    @GET("/api/recipes/v2")
    suspend fun recipeSearchQuery(): Response<RecipeResponseDataModel>

}