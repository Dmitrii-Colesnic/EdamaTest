package com.example.edamatest.ui.recipe_search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class RecipeSearchViewModel : ViewModel() {

    private val _macronutrientsList = MutableStateFlow(macronutrientsList())
    val macronutrientsList: StateFlow<List<NutrientsModel>> = _macronutrientsList.asStateFlow()

    fun setMacronutrientsItemRange(itemPosition: Int, value: String) {
        _macronutrientsList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(serverRange = value)
            newList
        }
    }

    fun clearMacronutrientsItemRange(itemPosition: Int) {
        _macronutrientsList.update { previousList ->
            val newList = previousList.toMutableList()
            newList[itemPosition] = newList[itemPosition].copy(serverRange = "")
            newList
        }
    }
}
