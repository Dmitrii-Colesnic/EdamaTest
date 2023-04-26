package com.example.domain.nutrition_analysis

import com.example.domain.ServerResponse
import com.example.domain.nutrition_analysis.model.NutritionAnalysisRequestDomainModel
import com.example.domain.nutrition_analysis.model.NutritionAnalysisResponseDomainModel

interface NutritionAnalysisRepo {

    suspend fun getNutritionAnalysis(
        appId: String,
        appKey: String,
        bodyModel: NutritionAnalysisRequestDomainModel
    ) : ServerResponse<NutritionAnalysisResponseDomainModel>

}