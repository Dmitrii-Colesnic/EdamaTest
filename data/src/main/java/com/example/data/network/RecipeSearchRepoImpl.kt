package com.example.data.network

import com.example.domain.recipe_search.RecipeSearchRepo
import com.example.domain.recipe_search.models.RecipeResponseDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeSearchRepoImpl(private val service: EdamamService) : RecipeSearchRepo {
    override suspend fun getRecipe(): Flow<RecipeResponseDomainModel?> {
        return flow {
            emit(
                map(service.recipeSearchQuery().body())
            )
        }
    }

    private fun map(response: RecipeResponseDataModel?): RecipeResponseDomainModel {

    }
}