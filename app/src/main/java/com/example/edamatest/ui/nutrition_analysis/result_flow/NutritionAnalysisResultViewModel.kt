package com.example.edamatest.ui.nutrition_analysis.result_flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResponseErrorDomain
import com.example.domain.ResponseExceptionDomain
import com.example.domain.ResponseSuccessDomain
import com.example.domain.nutrition_analysis.GetNutritionAnalysisUseCase
import kotlinx.coroutines.launch

class NutritionAnalysisResultViewModel(
    private val getNutritionAnalysisUseCase: GetNutritionAnalysisUseCase
) : ViewModel() {

    fun getNutritionAnalysis(products: List<String>) {

        viewModelScope.launch {
            getNutritionAnalysisUseCase.execute(products).let {
                when (it) {
                    is ResponseSuccessDomain -> {
                        Log.d("okhttp", "UIResponseSuccess")
                    }
                    is ResponseErrorDomain -> {
                        Log.d(
                            "okhttp",
                            "UIResponseError: responseCode = ${it.code},  responseMessage = ${it.message}"
                        )
                    }
                    is ResponseExceptionDomain -> {
                        Log.d(
                            "okhttp",
                            "UIResponseException - exceptionCode = ${it.e.message}"
                        )
                    }
                }
            }
        }

    }

}