package com.example.edamatest.ui.nutrition_analysis

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NutritionAnalysisViewModel : ViewModel() {

    private var _listOfItems: MutableStateFlow<List<EditTextState>> =
        MutableStateFlow(arrayListOf())
    val listOfItems: StateFlow<List<EditTextState>> = _listOfItems.asStateFlow()

    fun deleteItemClickEvent(value: EditTextState) {
        _listOfItems.update { previousList ->
            val newList = previousList.toMutableList()
            newList.remove(value)
            newList
        }
//        val oldList = _listOfItems.value.orEmpty().toMutableList()
//        oldList.remove(value)
//        _listOfItems.value = oldList
    }

    fun addItemClickEvent() {
        _listOfItems.update { previousList ->
            val newList = previousList.toMutableList()
            newList.add(EditTextState(previousList.size))
            newList
        }
//        val oldList = _listOfItems.value.orEmpty().toMutableList()
//        oldList.add(EditTextState(oldList.size))
//        _listOfItems.value = oldList
    }

}