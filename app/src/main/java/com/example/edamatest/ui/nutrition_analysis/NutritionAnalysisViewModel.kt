package com.example.edamatest.ui.nutrition_analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class NutritionAnalysisViewModel : ViewModel() {

    private var _listOfItems: MutableLiveData<List<EditTextState>> = MutableLiveData(arrayListOf())
    val listOfItems: LiveData<List<EditTextState>>  = _listOfItems

    fun deleteItemClickEvent(position: Int) {
        val oldList = _listOfItems.value.orEmpty().toMutableList()
        oldList.removeAt(position)
        _listOfItems.value = oldList
    }

    fun addItemClickEvent() {
        val oldList = _listOfItems.value.orEmpty().toMutableList()
        oldList.apply {
            if (isNotEmpty())
                last().btnIsActive = false
            add(EditTextState(oldList.lastIndex))
        }
        _listOfItems.value = oldList
    }

}