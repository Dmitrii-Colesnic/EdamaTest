package com.example.edamatest.ui.nutrition_analysis.result_flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.ResponseError
import com.example.domain.ServerResponseError
import com.example.domain.ServerResponseException
import com.example.domain.ServerResponseSuccess
import com.example.domain.nutrition_analysis.GetNutritionAnalysisUseCase
import com.example.edamatest.ui.model.ServerResponseErrorModel
import com.example.edamatest.ui.model.ServerResponseExceptionModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NutritionAnalysisResultViewModel(
    private val getNutritionAnalysisUseCase: GetNutritionAnalysisUseCase
) : ViewModel() {

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