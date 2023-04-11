package com.example.edamatest.ui.recipe_search.result_flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.recipe_search.GetRecipeUseCase
import com.example.domain.recipe_search.RecipeSearchResponseError
import com.example.domain.recipe_search.RecipeSearchResponseException
import com.example.domain.recipe_search.RecipeSearchResponseSuccess
import com.example.domain.recipe_search.models.Hits
import com.example.domain.recipe_search.models.Nutrient
import com.example.domain.recipe_search.models.RecipeResponseDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeSearchResultViewModel(private val recipeUseCase: GetRecipeUseCase) : ViewModel() {

    private val _recipeSearchResult = MutableStateFlow(listOf<RecipeSearchResultItem>())
    val recipeSearchResult: StateFlow<List<RecipeSearchResultItem>> =
        _recipeSearchResult.asStateFlow()

    private val _responseError = MutableStateFlow("")
    val responseError = _responseError.asStateFlow()

    private val _responseException = MutableStateFlow("")
    val responseException = _responseError.asStateFlow()

    private val _loadingEvent = MutableStateFlow(true)
    val loadingEvent = _loadingEvent.asStateFlow()

    fun getRecipeList(requestObject: RequestModel) {

        _loadingEvent.value = true
        viewModelScope.launch() {
            val response = recipeUseCase.execute(
                keyWord = requestObject.keyWord,
                diet = requestObject.diet,
                health = requestObject.health,
                cuisineType = requestObject.cuisineType,
                nutrients = requestObject.nutrients,
            )

            when (response) {
                is RecipeSearchResponseSuccess -> {
                    _recipeSearchResult.update {
                        withContext(Dispatchers.Default) {
                            response.data.hits.map { it.toUiModel() }
                        }
                    }
                    Log.d(
                        "okhttp",
                        "UIResponseSuccess"
                    )
                    _loadingEvent.value = false
                }
                is RecipeSearchResponseError -> {
                    _responseError.value =
                        "response code = ${response.code}  response message = ${response.message}"
                    Log.d(
                        "okhttp",
                        "UIResponseError - responseCode = ${response.code}  responseMessage = ${response.message}"
                    )
                }
                is RecipeSearchResponseException -> {
                    _responseException.value =
                        "exception code = ${response.e.message}"
                    Log.d(
                        "okhttp",
                        "UIResponseException - exceptionCode = ${response.e.message}"
                    )
                }
            }

        }
    }

}

private fun Hits.toUiModel() = RecipeSearchResultItem(
    uri = recipe.uri,
    label = recipe.label,
    image = recipe.image,
    source = recipe.source,
    url = recipe.url,
    calories = recipe.calories.toInt(),
    healthLabels = recipe.dietLabels + recipe.healthLabels,
    macroNutrients = MacroNutrients(
        PROCNT = recipe.totalNutrients.PROCNT.toUiModel(),
        FAT = recipe.totalNutrients.FAT.toUiModel(),
        CHOCDF = recipe.totalNutrients.CHOCDF.toUiModel()
    ),
    microNutrients = MicroNutrients(
        SUGAR = recipe.totalNutrients.SUGAR.toUiModel(),
        CHOLE = recipe.totalNutrients.CHOLE.toUiModel(),
        NA = recipe.totalNutrients.NA.toUiModel(),
        CA = recipe.totalNutrients.CA.toUiModel(),
        MG = recipe.totalNutrients.MG.toUiModel(),
        K = recipe.totalNutrients.K.toUiModel(),
        FE = recipe.totalNutrients.FE.toUiModel(),
    )
)

private fun Nutrient.toUiModel() = com.example.edamatest.ui.recipe_search.result_flow.Nutrient(
    label = label,
    quantity = quantity.toInt(),
    unit = unit
)
