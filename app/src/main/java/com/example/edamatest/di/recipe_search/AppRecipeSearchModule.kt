package com.example.edamatest.di.recipe_search

import com.example.edamatest.ui.recipe_search.RecipeSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appRecipeSearchModule = module {

    viewModel {
        RecipeSearchViewModel(
            recipeUseCase = get()
        )
    }

}