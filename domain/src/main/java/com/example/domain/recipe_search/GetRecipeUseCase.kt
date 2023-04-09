package com.example.domain.recipe_search

import com.example.domain.recipe_search.models.RecipeResponseDomainModel

private const val APPLICATION_ID = "0bcdf660"
private const val APPLICATION_KEYS = "618dae8b1a1226242ff8db8677dd5d50"

class GetRecipeUseCase(private val recipeSearchRepo: RecipeSearchRepo) {
    suspend fun execute(
        keyWord: String,
        calories: String,
        diet: List<String> = emptyList(),
        health: List<String> = emptyList(),
        cuisineType: List<String> = emptyList(),
        nutrients: Map<String, String> = emptyMap()
    ): RecipeSearchResponse<RecipeResponseDomainModel> {
        return recipeSearchRepo.getRecipe(
            appId = APPLICATION_ID,
            appKey = APPLICATION_KEYS,
            keyWord = keyWord,
            calories = calories,
            diet = diet,
            health = health,
            cuisineType = cuisineType,
            nutrients = nutrients
        )
    }
}