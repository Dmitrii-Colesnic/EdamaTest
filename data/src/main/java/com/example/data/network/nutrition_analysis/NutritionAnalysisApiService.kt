package com.example.data.network.nutrition_analysis

import com.example.data.network.*
import com.example.data.network.nutrition_analysis.model.NutritionAnalysisRequestDataModel
import com.example.data.network.nutrition_analysis.model.NutritionAnalysisResponseDataModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NutritionAnalysisApiService {
    @POST("/api/nutrition-details")
    suspend fun getAnalysis(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Body bodyModel: NutritionAnalysisRequestDataModel
    ): Response<NutritionAnalysisResponseDataModel>
}

class NutritionAnalysisApiRemoteSource(private val nutritionAnalysisApiService: NutritionAnalysisApiService) {
    suspend fun invokeGetAnalysis(
        appId: String,
        appKey: String,
        bodyModel: NutritionAnalysisRequestDataModel
    ): ApiResponse<NutritionAnalysisResponseDataModel> = handleApi {
        nutritionAnalysisApiService.getAnalysis(
            appId = appId,
            appKey = appKey,
            bodyModel = bodyModel
        )
    }
}