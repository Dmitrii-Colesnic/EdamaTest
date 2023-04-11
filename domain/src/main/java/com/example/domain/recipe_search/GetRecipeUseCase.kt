package com.example.domain.recipe_search

import com.example.domain.recipe_search.models.RecipeResponseDomainModel

private const val APPLICATION_ID = "0bcdf660"
private const val APPLICATION_KEYS = "618dae8b1a1226242ff8db8677dd5d50"
private const val TYPE = "public"

class GetRecipeUseCase(private val recipeSearchRepo: RecipeSearchRepo) {
    suspend fun execute(
        keyWord: String,
        diet: List<String> = emptyList(),
        health: List<String> = emptyList(),
        cuisineType: List<String> = emptyList(),
        nutrients: Map<String, String> = emptyMap()
    ): RecipeSearchResponse<RecipeResponseDomainModel> {
        return recipeSearchRepo.getRecipe(
            type = TYPE,
            appId = APPLICATION_ID,
            appKey = APPLICATION_KEYS,
            keyWord = keyWord,
            diet = diet,
            health = health,
            cuisineType = cuisineType,
            nutrients = nutrients
        )
    }
    suspend fun executeNext(
        url: String
    ): RecipeSearchResponse<RecipeResponseDomainModel> {
        return recipeSearchRepo.getRecipeNext(
            url = url
        )
    }
}