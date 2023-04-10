package com.example.edamatest.ui.recipe_search

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.ResponseError
import com.example.data.network.ResponseException
import com.example.data.network.ResponseSuccess
import com.example.domain.recipe_search.GetRecipeUseCase
import com.example.domain.recipe_search.RecipeSearchResponseError
import com.example.domain.recipe_search.RecipeSearchResponseException
import com.example.domain.recipe_search.RecipeSearchResponseSuccess
import com.example.edamatest.ui.recipe_search.adapter.CategoriesModel
import com.example.edamatest.ui.recipe_search.adapter.NutrientsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeSearchViewModel(private val recipeUseCase: GetRecipeUseCase) : ViewModel() {

//    private var _keyWord = MutableStateFlow("")
//    val keyWord: StateFlow<String> = _keyWord.asStateFlow()

    private var _calories =
        MutableStateFlow(NutrientsModel(name = "calories", serverName = "calories"))
    val caloriesMax: StateFlow<NutrientsModel> = _calories.asStateFlow()

    private val _healthList = MutableStateFlow(healthList())
    val healthList: StateFlow<List<CategoriesModel>> = _healthList.asStateFlow()

    private val _dietList = MutableStateFlow(dietList())
    val dietList: StateFlow<List<CategoriesModel>> = _dietList.asStateFlow()

    private val _cuisineTypeList = MutableStateFlow(cuisineList())
    val cuisineTypeList: StateFlow<List<CategoriesModel>> = _cuisineTypeList.asStateFlow()

    private val _macronutrientsList = MutableStateFlow(macronutrientsList())
    val macronutrientsList: StateFlow<List<NutrientsModel>> = _macronutrientsList.asStateFlow()

    private val _micronutrientsList = MutableStateFlow(micronutrientsList())
    val micronutrientsList: StateFlow<List<NutrientsModel>> = _micronutrientsList.asStateFlow()


    fun changeHealthListItemStatus(itemPosition: Int, currentStatus: Boolean) {
        _healthList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(isChecked = !currentStatus)
            newList
        }
    }

    fun changeDietListItemStatus(itemPosition: Int, currentStatus: Boolean) {
        _dietList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(isChecked = !currentStatus)
            newList
        }
    }

    fun setMacronutrientsItemRange(itemPosition: Int, min: Int, max: Int) {
        _macronutrientsList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(valueMin = min, valueMax = max)
            newList
        }
    }

    fun clearMacronutrientsItemRange(itemPosition: Int) {
        _macronutrientsList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(valueMin = 0, valueMax = 0)
            newList
        }
    }

    fun setMicronutrientsItemRange(itemPosition: Int, min: Int, max: Int) {
        _micronutrientsList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(valueMin = min, valueMax = max)
            newList
        }
    }

    fun clearMicronutrientsItemRange(itemPosition: Int) {
        _micronutrientsList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(valueMin = 0, valueMax = 0)
            newList
        }
    }

    fun collectData(keyword: String) {
        viewModelScope.launch() {
            val response = recipeUseCase.execute(
                keyWord = keyword,
                diet = _dietList.value.getSelected(),
                health = _healthList.value.getSelected(),
                cuisineType = _cuisineTypeList.value.getSelected(),
                nutrients = getNutrients(),
            )

            when (response) {
                is RecipeSearchResponseSuccess -> Log.d("okhttp", "ResponseSuccess")
                is RecipeSearchResponseError -> Log.d("okhttp", "ResponseError")
                is RecipeSearchResponseException -> {
                    Log.d("okhttp", "ResponseException - ${response.e.message}")
                }
            }

        }
    }

    private fun getNutrients(): Map<String, String> {
        val nutrientsMap = mutableMapOf<String, String>()

        _calories.value.apply {
            toServerFormatRange().let {
                if (it.isNotEmpty()) {
                    nutrientsMap[serverName] = it
                }
            }
        }

        for (item in _macronutrientsList.value) {
            item.toServerFormatRange().let {
                if (it.isNotEmpty()) {
                    nutrientsMap[item.serverName] = it
                }
            }
        }

        for (item in _micronutrientsList.value) {
            item.toServerFormatRange().let {
                if (it.isNotEmpty()) {
                    nutrientsMap[item.serverName] = it
                }
            }
        }

        return nutrientsMap
    }

    fun setCaloriesMin(min: Int) {
        _calories.value.valueMin = min
    }

    fun setCaloriesMax(max: Int) {
        _calories.value.valueMax = max
        {}
    }
}

    private fun List<CategoriesModel>.getSelected(): List<String> = this.mapNotNull {
        if (it.isChecked) {
            it.name
        } else {
            null
        }
    }