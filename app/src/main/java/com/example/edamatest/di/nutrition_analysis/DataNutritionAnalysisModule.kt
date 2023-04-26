package com.example.edamatest.di.nutrition_analysis

import com.example.data.network.nutrition_analysis.NutritionAnalysisApiRemoteSource
import com.example.data.network.nutrition_analysis.NutritionAnalysisRepoImpl
import com.example.domain.nutrition_analysis.NutritionAnalysisRepo
import org.koin.dsl.module

val dataNutritionAnalysisModule = module {

    single {
        NutritionAnalysisApiRemoteSource(
            nutritionAnalysisApiService = get()
        )
    }

    single<NutritionAnalysisRepo> {
        NutritionAnalysisRepoImpl(
            nutritionAnalysisApiRemoteSource = get()
        )
    }

}
