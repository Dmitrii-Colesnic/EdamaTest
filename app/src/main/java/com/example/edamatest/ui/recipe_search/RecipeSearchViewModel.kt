package com.example.edamatest.ui.recipe_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeSearchViewModel : ViewModel() {

    private val _macronutrientsList = MutableStateFlow(macronutrientsList())
    val macronutrientsList: StateFlow<List<NutrientsModel>> = _macronutrientsList.asStateFlow()

//    private val _macronutrientsList = MutableLiveData(macronutrientsList())
//    val macronutrientsList: LiveData<List<NutrientsModel>> = _macronutrientsList

    fun changeMacronutrientsListElement(item: NutrientsModel, value: String) {
        val newList = _macronutrientsList.value/*.orEmpty().toMutableList()*/
        newList[item.id - 1].range = value
        _macronutrientsList.update { newList }
    }
}
