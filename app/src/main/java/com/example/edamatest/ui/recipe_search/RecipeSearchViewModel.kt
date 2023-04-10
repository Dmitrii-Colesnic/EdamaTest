package com.example.edamatest.ui.recipe_search

import androidx.lifecycle.ViewModel
import com.example.edamatest.ui.recipe_search.adapter.CategoriesModel
import com.example.edamatest.ui.recipe_search.adapter.NutrientsModel
import com.example.edamatest.ui.recipe_search.result_flow.RequestModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecipeSearchViewModel : ViewModel() {

    private var _keyWord = MutableStateFlow("")

    private var _calories =
        MutableStateFlow(NutrientsModel(name = "calories", serverName = "calories"))

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

    fun  getCollectedData() : RequestModel {
        return RequestModel(
            keyWord = _keyWord.value,
            diet = _dietList.value.getSelected(),
            health = _healthList.value.getSelected(),
            cuisineType = _cuisineTypeList.value.getSelected(),
            nutrients = getNutrients(),
        )
    }

//            viewModelScope.launch() {
//                val response = recipeUseCase.execute(
//                    keyWord = keyword,
//                    diet = _dietList.value.getSelected(),
//                    health = _healthList.value.getSelected(),
//                    cuisineType = _cuisineTypeList.value.getSelected(),
//                    nutrients = getNutrients(),
//                )
//
//                when (response) {
//                    is RecipeSearchResponseSuccess -> {
//                        Log.d("okhttp", "ResponseSuccess")
//                    }
//                    is RecipeSearchResponseError -> {
//                        Log.d("okhttp", "ResponseError")
//                    }
//                    is RecipeSearchResponseException -> {
//                        Log.d("okhttp", "ResponseException - ${response.e.message}")
//                    }
//                }
//
//            }


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

//    fun setCaloriesMin(min: Int = 0) {
//        _calories.value.valueMin = min
//    }
    fun setCalories(min: Int = 0, max: Int = 0) {
        _calories.value.valueMin = min
        _calories.value.valueMax = max
    }

    fun setKeyWord(string: String) {
        _keyWord.value = string
    }

}

private fun List<CategoriesModel>.getSelected(): List<String> = this.mapNotNull {
    if (it.isChecked) {
        it.name
    } else {
        null
    }
}
