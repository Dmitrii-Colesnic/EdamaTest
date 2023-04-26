package com.example.data.network.nutrition_analysis

import android.util.Log
import com.example.data.network.ResponseError
import com.example.data.network.ResponseException
import com.example.data.network.ResponseSuccess
import com.example.data.network.nutrition_analysis.model.*
import com.example.domain.ServerResponse
import com.example.domain.ServerResponseError
import com.example.domain.ServerResponseException
import com.example.domain.ServerResponseSuccess
import com.example.domain.nutrition_analysis.NutritionAnalysisRepo
import com.example.domain.nutrition_analysis.model.*

class NutritionAnalysisRepoImpl(
    private val nutritionAnalysisApiRemoteSource: NutritionAnalysisApiRemoteSource
) : NutritionAnalysisRepo {

    override suspend fun getNutritionAnalysis(
        appId: String,
        appKey: String,
        bodyModel: NutritionAnalysisRequestDomainModel
    ): ServerResponse<NutritionAnalysisResponseDomainModel> {
        return try {
            val response = nutritionAnalysisApiRemoteSource.invokeGetAnalysis(
                appId = appId,
                appKey = appKey,
                bodyModel = bodyModel.toDomainModel()
            )

            response.let {
                when (it) {
                    is ResponseSuccess -> ServerResponseSuccess(
                        responseData = it.data.toDomainModel()
                    )
                    is ResponseError -> ServerResponseError(
                        responseCode = it.code, responseMessage = it.message
                    )
                    is ResponseException -> ServerResponseException(
                        exception = it.e
                    )
                }
            }
        } catch (e: Throwable) {
            Log.d("okhttp", "RepoImpl Mapping Exception - ${e.message}")
            ServerResponseException(exception = e)
        }
    }
}

private fun NutritionAnalysisRequestDomainModel.toDomainModel() =
    NutritionAnalysisRequestDataModel(
        ingr = ingr
    )

private fun NutritionAnalysisResponseDataModel.toDomainModel() =
    NutritionAnalysisResponseDomainModel(
        totalNutrients = totalNutrients.toDomainModel(),
        totalDaily = totalDaily.toDomainModel(),
        ingredients = ingredients.map { it.toDomain() },
        nutrientsKCal = totalNutrientsKCal.toDomainModel(),
    )

private fun TotalNutrientsData.toDomainModel() =
    TotalNutrientsDomain(
        ENERC_KCAL = ENERC_KCAL.toDomain(),
        FAT = FAT.toDomain(),
        FASAT = FASAT.toDomain(),
        FATRN = FATRN.toDomain(),
        CHOLE = CHOLE.toDomain(),
        NA = NA.toDomain(),
        CHOCDF = CHOCDF.toDomain(),
        FIBTG = FIBTG.toDomain(),
        SUGAR = SUGAR.toDomain(),
        PROCNT = PROCNT.toDomain(),
        VITD = VITD.toDomain(),
        CA = CA.toDomain(),
        FE = FE.toDomain(),
        K = K.toDomain()
    )

private fun TotalDailyData.toDomainModel() =
    TotalDailyDomain(
        FAT = FAT.toDomain(),
        FASAT = FASAT.toDomain(),
        CHOLE = CHOLE.toDomain(),
        NA = NA.toDomain(),
        CHOCDF = CHOCDF.toDomain(),
        FIBTG = FIBTG.toDomain(),
        PROCNT = PROCNT.toDomain(),
        VITD = VITD.toDomain(),
        CA = CA.toDomain(),
        FE = FE.toDomain(),
        K = K.toDomain()
    )

private fun IngredientData.toDomain() =
    IngredientDomain(
        text = text,
        parsed = parsed.map { it.toDomainModel() }
    )

private fun ParsedData.toDomainModel() =
    ParsedDomain(
        quantity = quantity,
        measure = measure,
        food = food,
        weight = weight.toInt(),
        nutrients = nutrients.toDomainModel()
    )

private fun IngredientNutrientsData.toDomainModel() =
    IngredientNutrientsDomain(
        ENERC_KCAL = ENERC_KCAL.toDomain(),
        FAT = FAT.toDomain(),
        PROCNT = PROCNT.toDomain(),
        CHOCDF = CHOCDF.toDomain(),
    )

private fun TotalNutrientsKCalData.toDomainModel() =
    TotalNutrientsKCalDomain(
        ENERC_KCAL = ENERC_KCAL.toDomain()
    )

private fun NutrientData.toDomain() =
    NutrientDomain(
        label = label,
        quantity = quantity.toInt(),
        unit = unit
    )

private fun TotalNutrientData.toDomain() =
    NutrientDomain(
        label = label,
        quantity = quantity.toInt(),
        unit = unit
    )
