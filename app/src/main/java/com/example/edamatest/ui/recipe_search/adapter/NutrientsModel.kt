package com.example.edamatest.ui.recipe_search.adapter

data class NutrientsModel(
    val name: String,
    val serverName: String,
    var valueMin: Int = 0,
    var valueMax: Int = 0,
)