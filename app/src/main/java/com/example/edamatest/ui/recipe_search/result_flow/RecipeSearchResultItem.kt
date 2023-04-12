package com.example.edamatest.ui.recipe_search.result_flow

data class RecipeSearchResultItem(
    val id: Int,
    val uri: String,
    val label: String,
    val image: String,
    val source: String,
    val url: String,
    val yield: Int,
    val calories: Int,
    val healthLabels: List<String>,
    val macroNutrients: MacroNutrients,
    val microNutrients: MicroNutrients,
    val ingredients: List<String>
)

data class MacroNutrients(
    val PROCNT: Nutrient,
    val FAT: Nutrient,
    val CHOCDF: Nutrient,
)

data class MicroNutrients(
    val SUGAR: Nutrient,
    val CHOLE: Nutrient,
    val NA: Nutrient,
    val CA: Nutrient,
    val MG: Nutrient,
    val K: Nutrient,
    val FE: Nutrient,
)

data class Nutrient(
    val label: String,
    val quantity: Int,
    val unit: String,
)
