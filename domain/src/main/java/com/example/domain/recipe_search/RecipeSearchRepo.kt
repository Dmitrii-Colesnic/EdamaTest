package com.example.domain.recipe_search

import com.example.domain.recipe_search.models.RecipeResponseDomainModel
import kotlinx.coroutines.flow.Flow

interface RecipeSearchRepo {
    suspend fun getRecipe() : Flow<RecipeResponseDomainModel?>
}