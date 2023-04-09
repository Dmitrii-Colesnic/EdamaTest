package com.example.edamatest.ui.recipe_search

import com.example.edamatest.ui.recipe_search.adapter.NutrientsModel

fun toDisplayFormatRange(valueMin: Int, valueMax: Int): String {
    if (valueMin != 0 && valueMax != 0) {
        return "($valueMin - $valueMax)"
    } else if (valueMin != 0) {
        return "(Min - $valueMin)"
    } else if (valueMax != 0) {
        return "(Max - $valueMax)"
    }
    return ""
}

fun NutrientsModel.toServerFormatRange(): String {
    return if (valueMin != 0 && valueMax != 0) {
        "$valueMin-$valueMax"
    } else if (valueMin != 0) {
        "$valueMax+"
    } else if (valueMax != 0) {
        valueMax.toString()
    } else {
        ""
    }
}

fun NutrientsModel.nutrientsToServerFormatRange(): String {
    var range = ""
    if (valueMin != 0 && valueMax != 0) {
        range = "$valueMin-$valueMax"
    } else if (valueMin != 0) {
        range = "$valueMax+"
    } else if (valueMax != 0) {
        range = valueMax.toString()
    }

    return if (range.isNotEmpty()) {
        "$serverName=$range"
    } else {
        ""
    }
}