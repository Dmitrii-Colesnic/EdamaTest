package com.example.domain.recipe_search

import com.example.domain.recipe_search.models.RecipeResponseDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipeUseCase(private val repo: RecipeSearchRepo) {

    fun execute() : Flow<RecipeResponseDomainModel?> {
        return flow {
            repo.getRecipe()
        }
    }

}