package com.example.data.network.recipe_search

import android.util.Log
import com.example.data.network.*
import com.example.data.network.recipe_search.model.RecipeResponseDataModel
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface RecipeApiService {

    @GET("/api/recipes/v2")
    suspend fun searchQuery(
        @Query("type") type: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("q") keyWord: String,
        @Query("diet") diet: List<String>,
        @Query("health") health: List<String>,
        @Query("cuisineType") cuisineType: List<String>,
        @QueryMap nutrients: Map<String, String>
    ): Response<RecipeResponseDataModel>

    @GET
    suspend fun searchQueryNext(
        @Url url: String
    ): Response<RecipeResponseDataModel>

}

class RecipeApiRemoteSource(private val recipeApiService: RecipeApiService) {
    suspend fun invokeSearchQuery(
        type: String,
        appId: String,
        appKey: String,
        keyWord: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients: Map<String, String>
    ): ApiResponse<RecipeResponseDataModel> = handleApiResponse {
            recipeApiService.searchQuery(
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

    suspend fun invokeSearchQueryNext(
        url: String
    ): ApiResponse<RecipeResponseDataModel> = handleApiResponse{
        recipeApiService.searchQueryNext(
            url = url
        )
    }
}

suspend fun <T : Any> handleApiResponse(
    execute: suspend () -> Response<T>
): ApiResponse<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ResponseSuccess(data = body)
        } else {
            ResponseError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ResponseError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Log.d("okhttp", "handleApi Exception - ${e.message}")
        ResponseException(e)
    }
}
