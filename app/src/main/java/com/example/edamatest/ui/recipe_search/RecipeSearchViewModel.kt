package com.example.edamatest.ui.recipe_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.recipe_search.GetRecipeUseCase
import com.example.domain.recipe_search.models.RecipeResponseDomainModel
import com.example.edamatest.ui.recipe_search.adapter.DietModel
import com.example.edamatest.ui.recipe_search.adapter.NutrientsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeSearchViewModel(private val useCase: GetRecipeUseCase) : ViewModel() {

    private val _healthList = MutableStateFlow(healthList())
    val healthList: StateFlow<List<DietModel>> = _healthList.asStateFlow()

    private val _dietList = MutableStateFlow(dietList())
    val dietList: StateFlow<List<DietModel>> = _dietList.asStateFlow()

    private val _macronutrientsList = MutableStateFlow(macronutrientsList())
    val macronutrientsList: StateFlow<List<NutrientsModel>> = _macronutrientsList.asStateFlow()

    private val _micronutrientsList = MutableStateFlow(micronutrientsList())
    val micronutrientsList: StateFlow<List<NutrientsModel>> = _micronutrientsList.asStateFlow()

    private val _cuisineTypeList = MutableStateFlow(cuisineList())
    val cuisineTypeList: StateFlow<List<CuisineTypeModel>> = _cuisineTypeList.asStateFlow()

    fun changeHealthListItemStatus(itemPosition: Int, currentStatus: Boolean) {
        _healthList.update {previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(isChecked = !currentStatus)
            newList
        }
    }

    fun changeDietListItemStatus(itemPosition: Int, currentStatus: Boolean) {
        _dietList.update {previousList ->
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

    fun collectData(keyword: String, caloriesMin: Int, caloriesMax: Int) {
        viewModelScope.launch {
            useCase.execute(

            ).collect {

            }
        }
    }
}
