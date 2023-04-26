package com.example.edamatest.di.nutrition_analysis

import com.example.domain.nutrition_analysis.GetNutritionAnalysisUseCase
import org.koin.dsl.module

val domainNutritionAnalysisModule = module {

    factory {
        GetNutritionAnalysisUseCase(
            nutritionAnalysisRepo = get()
        )
    }

}

