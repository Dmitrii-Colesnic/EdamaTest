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
        CategoriesModel(name = "balanced"),
        CategoriesModel(name = "high-Fiber"),
        CategoriesModel(name = "high-Protein"),
        CategoriesModel(name = "low-Carb"),
        CategoriesModel(name = "low-Fat"),
        CategoriesModel(name = "low-Sodium"),
)
}

fun healthList(): List<CategoriesModel> {
    return listOf(
        CategoriesModel(name = "alcohol-free"),
        CategoriesModel(name = "keto "),
        CategoriesModel(name = "kidney-friendly"),
        CategoriesModel(name = "kosher"),
        CategoriesModel(name = "low-potassium"),
        CategoriesModel(name = "no-oil-added"),
        CategoriesModel(name = "no-sugar"),
        CategoriesModel(name = "paleo"),
        CategoriesModel(name = "pescatarian"),
        CategoriesModel(name = "pork-free"),
        CategoriesModel(name = "red meat-free"),
        CategoriesModel(name = "sugar-conscious"),
        CategoriesModel(name = "vegan"),
        CategoriesModel(name = "vegetarian"),
        CategoriesModel(name = "celery-free"),
        CategoriesModel(name = "crustacean-free"),
        CategoriesModel(name = "dairy-free"),
        CategoriesModel(name = "egg-free"),
        CategoriesModel(name = "fish-free"),
        CategoriesModel(name = "gluten-free"),
        CategoriesModel(name = "lupine-free"),
        CategoriesModel(name = "mustard-free"),
        CategoriesModel(name = "peanut-free"),
        CategoriesModel(name = "sesame-free"),
        CategoriesModel(name = "shellfish-free"),
        CategoriesModel(name = "soy-free"),
        CategoriesModel(name = "tree-Nut-free"),
        CategoriesModel(name = "wheat-free"),
    )
}

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