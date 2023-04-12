package com.example.edamatest.ui.recipe_search.result_flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.recipe_search.GetRecipeUseCase
import com.example.domain.recipe_search.RecipeSearchResponseError
import com.example.domain.recipe_search.RecipeSearchResponseException
import com.example.domain.recipe_search.RecipeSearchResponseSuccess
import com.example.domain.recipe_search.models.Hits
import com.example.domain.recipe_search.models.Nutrient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

class RecipeSearchResultViewModel(private val recipeUseCase: GetRecipeUseCase) : ViewModel() {

    private val _recipeSearchResult = MutableSharedFlow<List<RecipeSearchResultItem>>(replay = 1)
    val recipeSearchResult: SharedFlow<List<RecipeSearchResultItem>> =
        _recipeSearchResult.asSharedFlow()

    private var nextPageURL: String? = null

    private val _responseError = MutableSharedFlow<String>(replay = 1)
    val responseError = _responseError.asSharedFlow()

    private val _responseException = MutableSharedFlow<String>(replay = 1)
    val responseException = _responseException.asSharedFlow()

    private val _loadingEvent = MutableStateFlow(true)
    val loadingEvent = _loadingEvent.asStateFlow()

    private val _loadingMoreItemsEvent = MutableStateFlow(false)
    val loadingMoreItemsEvent = _loadingMoreItemsEvent.asStateFlow()

    fun getRecipeList(requestObject: RequestModel) {

        _loadingEvent.value = true
        viewModelScope.launch {
            val response = recipeUseCase.execute(
                keyWord = requestObject.keyWord,
                diet = requestObject.diet,
                health = requestObject.health,
                cuisineType = requestObject.cuisineType,
                nutrients = requestObject.nutrients,
            )

            when (response) {
                is RecipeSearchResponseSuccess -> {
                    Log.d(
                        "okhttp",
                        "UIResponseSuccess"
                    )
                    nextPageURL = response.data._links.next?.href

                    _recipeSearchResult.tryEmit(
                        withContext(Dispatchers.Default) {
                            response.data.hits.map { it.toUiModel() }
                        }
                    )
                    _loadingEvent.value = false
                    _loadingMoreItemsEvent.value = false
                }
                is RecipeSearchResponseError -> {
                    _responseError.tryEmit(
                        "response code = ${response.code}"
                    )
                    Log.d(
                        "okhttp",
                        "UIResponseError: responseCode = ${response.code},  responseMessage = ${response.message}"
                    )
                    _loadingEvent.value = false
                    _loadingMoreItemsEvent.value = false
                }
                is RecipeSearchResponseException -> {
                    _responseException.tryEmit(
                        "exception code = ${response.e.message}"
                    )
                    Log.d(
                        "okhttp",
                        "UIResponseException - exceptionCode = ${response.e.message}"
                    )
                    _loadingEvent.value = false
                    _loadingMoreItemsEvent.value = false
                }
            }

        }
    }

    fun getRecipeListNext() {

            viewModelScope.launch {
                _loadingMoreItemsEvent.value = true
                if (nextPageURL == null) {
                    _loadingMoreItemsEvent.value = false
                    return@launch
                }

                val response = recipeUseCase.executeNext(
                    url = nextPageURL!!
                )

                when (response) {
                    is RecipeSearchResponseSuccess -> {
                        Log.d(
                            "okhttp",
                            "UIResponseSuccess"
                        )

                        nextPageURL = response.data._links.next?.href

                        _recipeSearchResult.tryEmit(
                            withContext(Dispatchers.Default) {
                                _recipeSearchResult.first() + response.data.hits.map { it.toUiModel() }
                            }
                        )
                        _loadingMoreItemsEvent.value = false
                    }
                    is RecipeSearchResponseError -> {
                        _responseError.tryEmit(
                            "response code = ${response.code}"
                        )
                        Log.d(
                            "okhttp",
                            "UIResponseError: responseCode = ${response.code},  responseMessage = ${response.message}"
                        )
                        _loadingMoreItemsEvent.value = false
                    }
                    is RecipeSearchResponseException -> {
                        _responseException.tryEmit(
                            "exception code = ${response.e.message}"
                        )
                        Log.d(
                            "okhttp",
                            "UIResponseException - exceptionCode = ${response.e.message}"
                        )
                        _loadingMoreItemsEvent.value = false
                    }
                }

            }
    }

    fun clearLoadingMoreItemsEvent() {
        _loadingMoreItemsEvent.value = false
    }

}

private var badCounter = 0
fun Hits.toUiModel() = RecipeSearchResultItem(
    id = ++badCounter,
    uri = recipe.uri,
    label = recipe.label,
    image = recipe.image,
    source = recipe.source,
    url = recipe.url,
    yield = recipe.yield.toInt(),
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
    ),
    ingredients = recipe.ingredientLines
)

private fun Nutrient.toUiModel() = com.example.edamatest.ui.recipe_search.result_flow.Nutrient(
    label = label,
    quantity = quantity.toInt(),
    unit = unit
)