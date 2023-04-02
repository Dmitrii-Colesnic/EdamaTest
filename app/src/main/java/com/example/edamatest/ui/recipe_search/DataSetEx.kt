package com.example.edamatest.ui.recipe_search

fun macronutrientsList(): List<NutrientsModel> {
    return listOf(
        NutrientsModel(id = 1, name = "Fat", serverName = "nutrients[FAT]"),
        NutrientsModel(id = 2, name = "Saturated", serverName = "nutrients[FASAT]"),
//        NutrientsModel(id = 3, name = "Trans", serverName = "nutrients[FATRN]"),
//        NutrientsModel(id = 4, name = "Monounsaturated", serverName = "nutrients[FAMS]"),
//        NutrientsModel(id = 5, name = "Polyunsaturated", serverName = "nutrients[FAPU]"),
//        NutrientsModel(id = 6, name = "Carbs", serverName = "nutrients[CHOCDF]"),
//        NutrientsModel(id = 7, name = "Fiber", serverName = "nutrients[FIBTG]"),
//        NutrientsModel(id = 8, name = "Sugar", serverName = "nutrients[SUGAR]"),
//        NutrientsModel(id = 9, name = "Protein", serverName = "nutrients[PROCNT]"),
    )
}