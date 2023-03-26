package com.example.edamatest.ui.nutrition_analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class NutritionAnalysisViewModel : ViewModel() {

    private var _listOfItems: MutableLiveData<ArrayList<EditTextState>> = MutableLiveData()
    val listOfItems: LiveData<ArrayList<EditTextState>> get() = _listOfItems

    init {
        _listOfItems.postValue(mutableListOf<EditTextState>() as ArrayList<EditTextState>?)
    }

    fun deleteItemClickEvent() {
        val oldList = ArrayList(_listOfItems.value.orEmpty())
        oldList.removeLast()
        _listOfItems.value = oldList
    }

    fun addItemClickEvent() {
        val oldList = ArrayList(_listOfItems.value.orEmpty())
        oldList.apply {
            if (isNotEmpty())
                last().btnIsActive = false
            add(EditTextState(oldList.lastIndex))
        }
        _listOfItems.value = oldList
    }

}