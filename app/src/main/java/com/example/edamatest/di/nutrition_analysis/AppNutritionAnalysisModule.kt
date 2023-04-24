package com.example.edamatest.di.nutrition_analysis

import com.example.edamatest.ui.nutrition_analysis.NutritionAnalysisViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appNutritionAnalysisModule = module {

    viewModel {
        NutritionAnalysisViewModel()
    }

}