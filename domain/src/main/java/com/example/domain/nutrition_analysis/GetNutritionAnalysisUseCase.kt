package com.example.domain.nutrition_analysis

import com.example.domain.ResponseDomain
import com.example.domain.nutrition_analysis.model.NutritionAnalysisRequestDomainModel
import com.example.domain.nutrition_analysis.model.NutritionAnalysisResponseDomainModel

private const val APPLICATION_ID = "117b023b"
private const val APPLICATION_KEYS = "3039d0c19999102c8f6b336fd6739d78"

class GetNutritionAnalysisUseCase(
    private val nutritionAnalysisRepo: NutritionAnalysisRepo
) {
    suspend fun execute(products: List<String>): ResponseDomain<NutritionAnalysisResponseDomainModel> {
        return nutritionAnalysisRepo.getNutritionAnalysis(
            appId = APPLICATION_ID,
            appKey = APPLICATION_KEYS,
            bodyModel = products.toBodyModel()
        )
    }

}

private fun List<String>.toBodyModel() =
    NutritionAnalysisRequestDomainModel(
        ingr = this
    )