package com.example.domain.recipe_search

import com.example.domain.ServerResponse
import com.example.domain.recipe_search.models.RecipeResponseDomainModel

interface RecipeSearchRepo {
    suspend fun getRecipe(
        type: String,
        appId: String,
        appKey: String,
        keyWord: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients: Map<String, String>
    ): ServerResponse<RecipeResponseDomainModel>

    suspend fun getRecipeNext(
        url: String
    ): ServerResponse<RecipeResponseDomainModel>
}
