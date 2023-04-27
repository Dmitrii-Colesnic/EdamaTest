package com.example.edamatest.ui.nutrition_analysis.result_flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ServerResponseError
import com.example.domain.ServerResponseException
import com.example.domain.ServerResponseSuccess
import com.example.domain.nutrition_analysis.GetNutritionAnalysisUseCase
import com.example.domain.nutrition_analysis.model.*
import com.example.edamatest.ui.StateFlowStatus
import com.example.edamatest.ui.model.ServerResponseErrorModel
import com.example.edamatest.ui.model.ServerResponseExceptionModel
import com.example.edamatest.ui.nutrition_analysis.model.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NutritionAnalysisResultViewModel(
    private val getNutritionAnalysisUseCase: GetNutritionAnalysisUseCase
) : ViewModel() {

    private val _ingredients = MutableStateFlow(emptyList<IngredientModel>())
    val ingredients = _ingredients.asStateFlow()

    private val _nutritionAnalysis = MutableStateFlow<StateFlowStatus<NutritionAnalysisModel>>(StateFlowStatus.Empty)
    val nutritionAnalysis = _nutritionAnalysis.asStateFlow()

    private val _responseError = Channel<ServerResponseErrorModel>()
    val responseError = _responseError.receiveAsFlow()

    private val _responseException = Channel<ServerResponseExceptionModel>()
    val responseException = _responseException.receiveAsFlow()

    private val _loadingEvent = MutableStateFlow(true)
    val loadingEvent = _loadingEvent.asStateFlow()

    fun getNutritionAnalysis(products: List<String>) {

        _loadingEvent.value = true
        viewModelScope.launch {
            getNutritionAnalysisUseCase.execute(products).let {
                when (it) {
                    is ServerResponseSuccess -> {
                        Log.d("okhttp", "UIResponseSuccess")

                        _ingredients.value =
                            it.responseData.ingredients.map { it -> it.toPresenterModel() }

                        _nutritionAnalysis.value = StateFlowStatus.Active(it.responseData.toPresenterModel())

                        _loadingEvent.value = false
                    }
                    is ServerResponseError -> {
                        Log.d(
                            "okhttp",
                            "UIResponseError: responseCode = ${it.responseCode},  responseMessage = ${it.responseMessage}"
                        )
                        when (it.responseCode) {
                            422 -> {
                                _responseError.send(
                                    ServerResponseErrorModel(
                                        responseCode = it.responseCode,
                                        responseMessage = "Unprocessable Entity - Couldn't parse the recipe or extract the nutritional info"
                                    )
                                )
                            }
                            555 -> {
                                _responseError.send(
                                    ServerResponseErrorModel(
                                        responseCode = it.responseCode,
                                        responseMessage = "Recipe with insufficient quality to process correctly"
                                    )
                                )
                            }
                            else -> {
                                _responseError.send(
                                    ServerResponseErrorModel(
                                        responseCode = it.responseCode,
                                        responseMessage = it.responseMessage.toString()
                                    )
                                )
                            }
                        }
                        _loadingEvent.value = false

                    }
                    is ServerResponseException -> {
                        Log.d(
                            "okhttp",
                            "UIResponseException: exceptionCode = ${it.exception.message}"
                        )
                        _responseException.send(ServerResponseExceptionModel(exceptionMessage = it.exception.message.toString()))
                        _loadingEvent.value = false
                    }
                }
            }
        }
    }

}

private fun NutritionAnalysisResponseDomainModel.toPresenterModel() =
    NutritionAnalysisModel(
        totalNutrients = totalNutrients.toPresenterModel(),
        totalDaily = totalDaily.toPresenterModel(),
        ENERC_KCAL = nutrientsKCal.ENERC_KCAL.toPresenterModel()
    )

private fun TotalNutrientsDomain.toPresenterModel() =
    TotalNutrientsModel(
        ENERC_KCAL = ENERC_KCAL.toPresenterModel(),
        FAT = FAT.toPresenterModel(),
        FASAT = FASAT.toPresenterModel(),
        FATRN = FATRN.toPresenterModel(),
        CHOLE = CHOLE.toPresenterModel(),
        NA = NA.toPresenterModel(),
        CHOCDF = CHOCDF.toPresenterModel(),
        FIBTG = FIBTG.toPresenterModel(),
        SUGAR = SUGAR.toPresenterModel(),
        PROCNT = PROCNT.toPresenterModel(),
        VITD = VITD.toPresenterModel(),
        CA = CA.toPresenterModel(),
        FE = FE.toPresenterModel(),
        K = K.toPresenterModel(),
    )

private fun TotalDailyDomain.toPresenterModel() =
    TotalDailyModel(
        FAT = FAT.toPresenterModel(),
        FASAT = FASAT.toPresenterModel(),
        CHOLE = CHOLE.toPresenterModel(),
        NA = NA.toPresenterModel(),
        CHOCDF = CHOCDF.toPresenterModel(),
        FIBTG = FIBTG.toPresenterModel(),
        PROCNT = PROCNT.toPresenterModel(),
        VITD = VITD.toPresenterModel(),
        CA = CA.toPresenterModel(),
        FE = FE.toPresenterModel(),
        K = K.toPresenterModel(),
    )

private fun IngredientDomain.toPresenterModel() =
    IngredientModel(
        text = text,
        parsed = parsed.map { it.toPresenterModel() }
    )

private fun ParsedDomain.toPresenterModel() =
    ParsedModel(
        quantity = quantity,
        measure = measure,
        food = food,
        weight = weight,
        nutrients = nutrients.toPresenterModel()
    )

private fun IngredientNutrientsDomain.toPresenterModel() =
    IngredientNutrientsModel(
        ENERC_KCAL = ENERC_KCAL.toPresenterModel(),
        FAT = FAT.toPresenterModel(),
        PROCNT = PROCNT.toPresenterModel(),
        CHOCDF = CHOCDF.toPresenterModel(),
    )

private fun NutrientDomain.toPresenterModel() =
    NutrientModel(
        label = label,
        quantity = quantity,
        unit = unit
    )