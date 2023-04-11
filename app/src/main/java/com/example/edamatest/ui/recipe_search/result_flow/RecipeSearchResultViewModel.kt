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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeSearchResultViewModel(private val recipeUseCase: GetRecipeUseCase) : ViewModel() {

    private val _recipeSearchResult = MutableSharedFlow<List<RecipeSearchResultItem>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val recipeSearchResult: SharedFlow<List<RecipeSearchResultItem>> =
        _recipeSearchResult.asSharedFlow()

//    private val _recipeSearchResult = MutableStateFlow<List<RecipeSearchResultItem>>(emptyList())
//    val recipeSearchResult: StateFlow<List<RecipeSearchResultItem>> =
//        _recipeSearchResult.asStateFlow()

    private var nextPageURL: String? = null

    private val _responseError = MutableSharedFlow<String>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val responseError = _responseError.asSharedFlow()

    private val _responseException = MutableSharedFlow<String>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val responseException = _responseError.asSharedFlow()

    private val _loadingEvent = MutableStateFlow(true)
    val loadingEvent = _loadingEvent.asStateFlow()

    private val _isLastPage = MutableStateFlow(false)
    val isLastPage = _isLastPage.asStateFlow()

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
//                    _recipeSearchResult.update {
//                        withContext(Dispatchers.Default) {
//                            response.data.hits.map { it.toUiModel() }
//                        }
//                    }
                    _recipeSearchResult.tryEmit(
                        withContext(Dispatchers.Default) {
                            response.data.hits.map { it.toUiModel() }
                        }
                    )
                    _loadingEvent.value = false
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
                }
            }

        }
    }

    fun getRecipeListNext() {

        if (nextPageURL != null) {
            viewModelScope.launch {
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
//                        _recipeSearchResult.update { prevList ->
//                            withContext(Dispatchers.Default) {
//                                val newList = ArrayList(prevList)
//                                response.data.hits.map { newList.add(it.toUiModel()) }
//                                newList
//                            }
//                        }
                        _recipeSearchResult.collect { oldList ->
                            withContext(Dispatchers.Default) {
                                val newList = ArrayList(oldList)
                                response.data.hits.map { newList.add(it.toUiModel()) }
                                _recipeSearchResult.tryEmit(newList)
                            }
                        }
                    }
                    is RecipeSearchResponseError -> {
                        _responseError.tryEmit(
                            "response code = ${response.code}"
                        )
                        Log.d(
                            "okhttp",
                            "UIResponseError: responseCode = ${response.code},  responseMessage = ${response.message}"
                        )
                    }
                    is RecipeSearchResponseException -> {
                        _responseException.tryEmit(
                            "exception code = ${response.e.message}"
                        )
                        Log.d(
                            "okhttp",
                            "UIResponseException - exceptionCode = ${response.e.message}"
                        )
                    }
                }

            }
        } else {
            _isLastPage.value = true
        }
    }

}

var badCounter = 0
private fun Hits.toUiModel() = RecipeSearchResultItem(
    id = ++badCounter,
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
