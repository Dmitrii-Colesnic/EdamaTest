package com.example.edamatest.ui.nutrition_analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class NutritionAnalysisViewModel : ViewModel() {

    private var _listOfItems: MutableLiveData<List<EditTextState>> = MutableLiveData(arrayListOf())
    val listOfItems: LiveData<List<EditTextState>> = _listOfItems

    private var _recipe: MutableLiveData<String> = MutableLiveData()

    fun deleteItemClickEvent(value: EditTextState) {
        val oldList = _listOfItems.value.orEmpty().toMutableList()
        oldList.remove(value)
        _listOfItems.value = oldList
    }

    fun addItemClickEvent() {
        val oldList = _listOfItems.value.orEmpty().toMutableList()
        oldList.add(EditTextState(oldList.size))
        _listOfItems.value = oldList
    }

    fun getRecipe() {
        var recipe = ""
        for (item in _listOfItems.value!!) {
            recipe += item.input
        }
        _recipe.value = recipe
    }

}