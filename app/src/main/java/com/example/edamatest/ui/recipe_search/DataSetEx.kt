package com.example.edamatest.ui.recipe_search

import com.example.edamatest.ui.recipe_search.adapter.CategoriesModel
import com.example.edamatest.ui.recipe_search.adapter.NutrientsModel

fun macronutrientsList(): List<NutrientsModel> {
    return listOf(
        NutrientsModel(name = "Fat",                serverName = "nutrients[FAT]"),
        NutrientsModel(name = "Saturated",          serverName = "nutrients[FASAT]"),
        NutrientsModel(name = "Trans",              serverName = "nutrients[FATRN]"),
        NutrientsModel(name = "Monounsaturated",    serverName = "nutrients[FAMS]"),
        NutrientsModel(name = "Polyunsaturated",    serverName = "nutrients[FAPU]"),
        NutrientsModel(name = "Carbs",              serverName = "nutrients[CHOCDF]"),
        NutrientsModel(name = "Fiber",              serverName = "nutrients[FIBTG]"),
        NutrientsModel(name = "Sugar",              serverName = "nutrients[SUGAR]"),
        NutrientsModel(name = "Protein",            serverName = "nutrients[PROCNT]"),
    )
}

fun micronutrientsList(): List<NutrientsModel> {
    return listOf(
        NutrientsModel(name = "Cholesterol",            serverName = "nutrients[CHOLE]"),
        NutrientsModel(name = "Sodium",                 serverName = "nutrients[NA]"),
        NutrientsModel(name = "Calcium",                serverName = "nutrients[CA]"),
        NutrientsModel(name = "Magnesium",              serverName = "nutrients[MG]"),
        NutrientsModel(name = "Potassium",              serverName = "nutrients[K]"),
        NutrientsModel(name = "Iron",                   serverName = "nutrients[FE]"),
        NutrientsModel(name = "Phosphorus",             serverName = "nutrients[P]"),
        NutrientsModel(name = "Vitamin A",              serverName = "nutrients[VITA_RAE]"),
        NutrientsModel(name = "Vitamin C",              serverName = "nutrients[VITC]"),
        NutrientsModel(name = "Thiamin (B1)",           serverName = "nutrients[THIA]"),
        NutrientsModel(name = "Riboflavin (B2)",        serverName = "nutrients[RIBF]"),
        NutrientsModel(name = "Niacin (B3)",            serverName = "nutrients[NIA]"),
        NutrientsModel(name = "Vitamin B6",             serverName = "nutrients[VITB6A]"),
        NutrientsModel(name = "Folate (Equivalent)",    serverName = "nutrients[FOLDFE]"),
        NutrientsModel(name = "Vitamin B12",            serverName = "nutrients[VITB12]"),
        NutrientsModel(name = "Vitamin D",              serverName = "nutrients[VITD]"),
        NutrientsModel(name = "Vitamin E",              serverName = "nutrients[TOCPHA]"),
        NutrientsModel(name = "Vitamin K",              serverName = "nutrients[VITK1]"),
    )
}

fun dietList(): List<CategoriesModel> {
    return listOf(
        CategoriesModel(name = "Balanced"),
        CategoriesModel(name = "High-Fiber"),
        CategoriesModel(name = "High-Protein"),
        CategoriesModel(name = "Low-Carb"),
        CategoriesModel(name = "Low-Fat"),
        CategoriesModel(name = "Low-Sodium"),
)
}

fun healthList(): List<CategoriesModel> {
    return listOf(
        CategoriesModel(name = "Alcohol-free"),
        CategoriesModel(name = "Keto "),
        CategoriesModel(name = "Kidney-friendly"),
        CategoriesModel(name = "Kosher"),
        CategoriesModel(name = "Low-potassium"),
        CategoriesModel(name = "No-oil-added"),
        CategoriesModel(name = "No-sugar"),
        CategoriesModel(name = "Paleo"),
        CategoriesModel(name = "Pescatarian"),
        CategoriesModel(name = "Pork-free"),
        CategoriesModel(name = "Red meat-free"),
        CategoriesModel(name = "Sugar-conscious"),
        CategoriesModel(name = "Vegan"),
        CategoriesModel(name = "Vegetarian"),
        CategoriesModel(name = "Celery-free"),
        CategoriesModel(name = "Crustacean-free"),
        CategoriesModel(name = "Dairy-free"),
        CategoriesModel(name = "Egg-free"),
        CategoriesModel(name = "Fish-free"),
        CategoriesModel(name = "Gluten-free"),
        CategoriesModel(name = "Lupine-free"),
        CategoriesModel(name = "Mustard-free"),
        CategoriesModel(name = "Peanut-free"),
        CategoriesModel(name = "Sesame-free"),
        CategoriesModel(name = "Shellfish-free"),
        CategoriesModel(name = "Soy-free"),
        CategoriesModel(name = "Tree-Nut-free"),
        CategoriesModel(name = "Wheat-free"),
    )
}
    data class CuisineTypeModel (
        val name: String,
        var isChecked: Boolean = false,
    )

fun cuisineList(): List<CategoriesModel> {
    return listOf(
        CategoriesModel(name = "American"),
        CategoriesModel(name = "Asian"),
        CategoriesModel(name = "British"),
        CategoriesModel(name = "Caribbean"),
        CategoriesModel(name = "Central europe"),
        CategoriesModel(name = "Chinese"),
        CategoriesModel(name = "Eastern europe"),
        CategoriesModel(name = "French"),
        CategoriesModel(name = "Greek"),
        CategoriesModel(name = "Indian"),
        CategoriesModel(name = "Italian"),
        CategoriesModel(name = "Japanese"),
        CategoriesModel(name = "Korean"),
        CategoriesModel(name = "Kosher"),
        CategoriesModel(name = "Mediterranean"),
        CategoriesModel(name = "Mexican"),
        CategoriesModel(name = "Middle eastern"),
        CategoriesModel(name = "Nordic"),
        CategoriesModel(name = "South american"),
        CategoriesModel(name = "South east asian"),
)
}