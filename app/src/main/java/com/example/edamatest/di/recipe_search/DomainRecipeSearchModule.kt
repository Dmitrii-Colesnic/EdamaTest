package com.example.edamatest.di.recipe_search

import com.example.domain.recipe_search.GetRecipeUseCase
import org.koin.dsl.module

val domainRecipeSearchModule = module {

    factory {
        GetRecipeUseCase(
            recipeSearchRepo = get()
        )
    }

}