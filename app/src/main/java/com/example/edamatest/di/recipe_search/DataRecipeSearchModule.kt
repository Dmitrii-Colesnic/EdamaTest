package com.example.edamatest.di.recipe_search

import com.example.data.network.recipe_search.RecipeApiRemoteSource
import com.example.data.network.recipe_search.RecipeSearchRepoImpl
import com.example.domain.recipe_search.RecipeSearchRepo
import org.koin.dsl.module

val dataRecipeSearchModule = module {

    single {
        RecipeApiRemoteSource(
            recipeApiService = get()
        )
    }

    single<RecipeSearchRepo> {
        RecipeSearchRepoImpl(
            recipeApiRemoteSource = get()
        )
    }

}