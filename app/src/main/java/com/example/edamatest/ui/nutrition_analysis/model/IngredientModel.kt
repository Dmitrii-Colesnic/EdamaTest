package com.example.edamatest.ui.nutrition_analysis.model


data class IngredientModel(
    val quantity: Long,
    val measure: String,
    val food: String,
    val foodId: String,
    val weight: Int,
    val nutrients: IngredientNutrientsModel,
)

data class IngredientNutrientsModel(
    val ENERC_KCAL: NutrientModel,
    val FAT: NutrientModel,
    val PROCNT: NutrientModel,
    val CHOCDF: NutrientModel,
)
