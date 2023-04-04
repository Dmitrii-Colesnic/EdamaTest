package com.example.edamatest.di

import com.example.edamatest.ui.recipe_search.RecipeSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<RecipeSearchViewModel>() {
        RecipeSearchViewModel()
    }

}