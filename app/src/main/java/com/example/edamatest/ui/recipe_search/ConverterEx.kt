package com.example.edamatest.ui.recipe_search

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

fun toServerFormatRange(valueMin: Int, valueMax: Int): String {
    if (valueMin != 0 && valueMax != 0) {
        return "$valueMin-$valueMax"
    } else if (valueMin != 0) {
        return "$valueMax+"
    } else if (valueMax != 0) {
        return valueMax.toString()
    }
    return ""
}