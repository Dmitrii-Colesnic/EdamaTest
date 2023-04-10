package com.example.edamatest.ui.recipe_search.result_flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.recipe_search.GetRecipeUseCase
import com.example.domain.recipe_search.RecipeSearchResponseError
import com.example.domain.recipe_search.RecipeSearchResponseException
import com.example.domain.recipe_search.RecipeSearchResponseSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeSearchResultViewModel(private val recipeUseCase: GetRecipeUseCase) : ViewModel() {

    private val _recipeSearchResult = MutableStateFlow(emptyList<RecipeSearchResultItem>())
    val recipeSearchResult: StateFlow<List<RecipeSearchResultItem>> = _recipeSearchResult.asStateFlow()

    fun getRecipeList(requestObject: RequestModel) {

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
                    Log.d("okhttp", "ResponseSuccess")


                }
                is RecipeSearchResponseError -> {
                    Log.d("okhttp", "ResponseError")
                }
                is RecipeSearchResponseException -> {
                    Log.d("okhttp", "ResponseException - ${response.e.message}")
                }
            }

        }
    }

}