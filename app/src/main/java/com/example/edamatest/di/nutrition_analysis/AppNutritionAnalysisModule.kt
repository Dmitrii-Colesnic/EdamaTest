package com.example.edamatest.di.nutrition_analysis

import com.example.edamatest.ui.nutrition_analysis.NutritionAnalysisViewModel
import com.example.edamatest.ui.nutrition_analysis.result_flow.NutritionAnalysisResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appNutritionAnalysisModule = module {

    viewModel {
        NutritionAnalysisViewModel()
    }

    viewModel {
        NutritionAnalysisResultViewModel(
            getNutritionAnalysisUseCase = get()
        )
    }

}