package com.example.data.network.recipe_search

import com.example.data.network.ApiResponse
import com.example.data.network.handleApi
import com.example.data.network.recipe_search.model.RecipeResponseDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RecipeApiService {

    @GET("/api/recipes/v2")
    suspend fun recipeSearchQuery(
        @Query("type") type: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("q") keyWord: String,
        @Query("diet") diet: List<String>,
        @Query("health") health: List<String>,
        @Query("cuisineType") cuisineType: List<String>,
        @QueryMap nutrients: Map<String, String>
    ): Response<RecipeResponseDataModel>

}

class RecipeApiRemoteSource(private val recipeApiService: RecipeApiService) {
    suspend operator fun invoke(
        type: String,
        appId: String,
        appKey: String,
        keyWord: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients: Map<String, String>
    ): ApiResponse<RecipeResponseDataModel> = handleApi {
            recipeApiService.recipeSearchQuery(
                type = type,
                appId = appId,
                appKey = appKey,
                keyWord = keyWord,
                diet = diet,
                health = health,
                cuisineType = cuisineType,
                nutrients = nutrients
            )
        }
}
